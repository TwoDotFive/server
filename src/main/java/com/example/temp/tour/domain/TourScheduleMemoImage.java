package com.example.temp.tour.domain;

import com.example.temp.common.entity.BaseTimeEntity;
import com.example.temp.common.util.IdUtil;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

@Entity
@Getter
@Table(name = "tour_schedule_memo_image")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TourScheduleMemoImage extends BaseTimeEntity implements Persistable<Long> {

    @Id
    private Long id;

    @JoinColumn(name = "memo_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private TourScheduleMemo memo;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private Integer sequence;

    public TourScheduleMemoImage(TourScheduleMemo memo, String imageUrl, Integer sequence) {
        this.id = IdUtil.create();
        this.memo = memo;
        this.imageUrl = imageUrl;
        this.sequence = sequence;
    }

    public void update(String imageUrl, int sequence) {
        this.imageUrl = imageUrl;
        this.sequence = sequence;
    }

    @Override
    public boolean isNew() {
        return getCreatedAt() == null;
    }
}
