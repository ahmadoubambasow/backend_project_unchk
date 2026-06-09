package com.unchk.backend.dashboard.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardItemDTO {

    private String label;

    private Long value;
}