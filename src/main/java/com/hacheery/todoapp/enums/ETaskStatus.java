package com.hacheery.todoapp.enums;

public enum ETaskStatus {
    TO_DO,        // Task đã được tạo nhưng chưa bắt đầu
    IN_PROGRESS,  // Task đang được thực hiện
    COMPLETED,    // Task đã hoàn thành
    ON_HOLD,      // Task bị tạm dừng
    CANCELLED,    // Task bị hủy bỏ
    REVIEW,       // Task đang được xem xét
    ARCHIVED      // Task đã được lưu trữ
}
