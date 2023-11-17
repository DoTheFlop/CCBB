package com.D104.ccbb.payment_history.domain;

import com.D104.ccbb.user.domain.User;
import com.D104.ccbb.vote.domain.Vote;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id", nullable = false)
    private Integer historyId;

    @Column(nullable = false, columnDefinition = "int")
    private Integer amount;

    @Column(name = "is_returned", nullable = false)
    private Boolean isReturned;

    @Column(name = "pay_date", nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime payDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @ToString.Exclude
    private User userId;

    @ManyToOne
    @JoinColumn(name = "vote_id")
    @ToString.Exclude
    private Vote voteId;

    @Column(name = "tid", nullable = false)
    private String tid;

    @Column(name = "is_approve", nullable = false)
    private boolean isApprove;

    @Column(name = "partner_order_id", nullable = false)
    private String partnerOrderId;
}
