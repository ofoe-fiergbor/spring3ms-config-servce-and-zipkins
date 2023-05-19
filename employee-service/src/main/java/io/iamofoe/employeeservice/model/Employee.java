package io.iamofoe.employeeservice.model;

public record Employee(long id, long departmentId, String name, int age) {
}
