CREATE TABLE LoopUser (
    UserId UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    FirstName VARCHAR(100) NOT NULL,
    MiddleName VARCHAR(100) NULL,
    LastName VARCHAR(100) NOT NULL,
    EmailAddress VARCHAR(100) NOT NULL,
    Password VARCHAR(255) NOT NULL
);

CREATE TABLE LoopAccount (
    AccountId UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    Iban VARCHAR(20) NOT NULL,
    BicSwift VARCHAR(20) NOT NULL,
    ClientId UUID REFERENCES LoopUser(UserId) NOT NULL
);

CREATE TABLE LoopCard (
    CardId UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    CardAlias VARCHAR(20) NOT NULL,
    AccountId UUID REFERENCES LoopAccount(AccountId) NOT NULL,
    CardType VARCHAR(10) NOT NULL
);

INSERT INTO LoopUser (UserId, FirstName, MiddleName, LastName, EmailAddress, Password) VALUES
(gen_random_uuid(), 'Christine', '', 'Mugo', 'christine.mugo@example.com', 'password123'),
(gen_random_uuid(), 'Mohammed', 'Joho', 'Ali', 'mohammed.ali@example.com', 'securepass'),
(gen_random_uuid(), 'James', '', 'Kipchoge', 'james.kipchoge@example.com', 'pass321'),
(gen_random_uuid(), 'Grace', 'Atieno', 'Akinyi', 'grace.akinyi@example.com', 'strongpass'),
(gen_random_uuid(), 'John', 'Peter', 'Karanja', 'john.karanja@example.com', 'mypassword');


