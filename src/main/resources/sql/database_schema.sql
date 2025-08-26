CREATE TABLE Users (
    user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL
    role VARCHAR(255) NOT NULL
);

CREATE TABLE JobApplications (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    jobTitle VARCHAR(255),
    companyName VARCHAR(255),
    status VARCHAR(255),
    dateApplied DATE,
    notes VARCHAR(255),
    user_id BIGINT NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

