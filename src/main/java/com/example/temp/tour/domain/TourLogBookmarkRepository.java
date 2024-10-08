package com.example.temp.tour.domain;

import com.example.temp.common.exception.CustomException;
import com.example.temp.tour.dto.BookmarkedTourLogPreviewNativeDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

public interface TourLogBookmarkRepository extends Repository<TourLogBookmark, Long> {

    void save(TourLogBookmark entity);

    Optional<TourLogBookmark> findById(Long id);

    default TourLogBookmark findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Invalid Bookmark Id"));
    }

    boolean existsByUserIdAndTourLogId(Long userId, Long tourLogId);

    Optional<TourLogBookmark> findByUserIdAndTourLogId(Long userId, Long tourLogId);

    default TourLogBookmark findByUserIdAndTourLogIdOrElseThrow(Long userId, Long tourLogId) {
        return findByUserIdAndTourLogId(userId, tourLogId)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "No Bookmark Exists"));
    }

    void delete(TourLogBookmark entity);

    @Query(
            nativeQuery = true,
            value = "SELECT b.id as bookmarkId, t.id as tourLogId, t.image_url as image, t.title as title, u.nickname as userNickname, u.profile_image_url as userProfileImage, s.shorten_name as stadiumName, " +
                    "SUBSTRING_INDEX(GROUP_CONCAT(DISTINCT REPLACE(ts.tour_place_name, ' ', '')), ',', 3) AS places " +
                    "FROM tour_log_bookmark b " +
                    "JOIN tour_log t on t.id = b.tour_log_id " +
                    "JOIN user u ON t.user_id = u.id " +
                    "JOIN tour_log_stadium s ON t.stadium_id = s.id " +
                    "LEFT JOIN tour_schedule ts ON ts.tour_log_id = t.id " +
                    "WHERE b.user_id = :userId AND b.id < :lastId " +
                    "GROUP BY b.id " + // b.id를 기준으로 그룹화하여 각 북마크에 대한 places를 계산
                    "ORDER BY b.id DESC " +
                    "LIMIT :pageSize"
    )
    List<BookmarkedTourLogPreviewNativeDto> findUserBookmarkedTourLogPreviewList(
            @Param("userId") Long userId,
            @Param("lastId") Long lastBookmarkId,
            @Param("pageSize") int pageSize
    );
}
