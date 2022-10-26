CREATE DATABASE DB_BTL_OOP;

GO
    USE DB_BTL_OOP;

GO
    CREATE TABLE Users(
        [ID] INT IDENTITY(1, 1),
        [Dob] DATE NOT NULL,
        [Email] NVARCHAR(30) NOT NULL UNIQUE,
        [RoleID] INT NOT NULL,
        [Status] BIT NOT NULL,
        [Name] NVARCHAR(30) NOT NULL,
        [MainImage] NTEXT NOT NULL,
        [CreatedDate] DATETIME NOT NULL,
        [CreatedByUserID] INT NOT NULL,
        [LastestModifiedDate] DATETIME NOT NULL,
        [LastestModifiedByUserID] INT NOT NULL,
        PRIMARY KEY ([ID])
    )
GO
    CREATE TABLE UserLogins (
        [UserID] INT NOT NULL,
        [UserName] NVARCHAR(30) NOT NULL UNIQUE,
        [Password] Nvarchar(64) NOT NULL,
        PRIMARY KEY ([UserID]),
        FOREIGN KEY ([UserID]) REFERENCES Users([ID])
    )
GO
    CREATE TABLE Students(
        [UserID] INT NOT NULL,
        [ClassCode] NVARCHAR(15) NOT NULL,
        [Sector] NVARCHAR(10) NOT NULL,
        [StudentCode] Nvarchar(15) NOT NULL,
        PRIMARY KEY ([UserID]),
        FOREIGN KEY ([UserID]) REFERENCES Users([ID])
    )
GO
    CREATE TABLE Categories(
        [ID] INT IDENTITY(1, 1),
        [Sector] NVARCHAR(10),
        [SectorName] NTEXT NOT NULL,
        [Year] TINYINT NOT NULL,
        [Term] TINYINT NOT NULL,
        PRIMARY KEY ([ID])
    )
GO
    CREATE TABLE Classes(
        [ID] INT IDENTITY(1, 1),
        [CourseID] NVARCHAR(10) NOT NULL,
        [CourseName] NTEXT NOT NULL,
        [StudyGroup] TINYINT NOT NULL,
        [PracticeGroup] TINYINT NOT NULL,
        [ClassCode] NVARCHAR(10) NOT NULL,
        [CategoryID] INT NOT NULL,
        [Status] BIT NOT NULL,
        PRIMARY KEY ([ID]),
        FOREIGN KEY ([CategoryID]) REFERENCES Categories([ID])
    )
GO
    CREATE TABLE UserHasClasses(
        [ID] INT NOT NULL,
        [UserID] INT NOT NULL,
        [ClassID] INT NOT NULL,
        [Status] BIT NOT NULL,
        PRIMARY KEY([ID]),
        FOREIGN KEY([UserID]) REFERENCES Users([ID]),
        FOREIGN KEY([ClassID]) REFERENCES Classes([ID]),
    )
GO
    CREATE TABLE Tradings (
        [ID] INT IDENTITY(1, 1),
        [ClassID] INT NOT NULL,
        [UserID] INT NOT NULL,
        [Status] BIT NOT NULL,
        [CreatedDate] DATETIME NOT NULL,
        [LastestModifiedDate] DATETIME NOT NULL,
        PRIMARY KEY ([ID]),
        FOREIGN KEY([UserID]) REFERENCES Users([ID]),
        FOREIGN KEY([UserID]) REFERENCES Classes([ID])
    )
GO
    CREATE TABLE TradingWishes (
        [ID] INT IDENTITY(1, 1),
        [TradingID] INT NOT NULL,
        [ClassID] INT NOT NULL,
        PRIMARY KEY ([ID]),
        FOREIGN KEY ([TradingID]) REFERENCES Tradings([ID]),
        FOREIGN KEY ([ClassID]) REFERENCES Classes([ID])
    )
GO
    CREATE TABLE JoinTradings(
        [ID] INT IDENTITY(1, 1),
        [TradingID] INT NOT NULL,
        [UserID] INT NOT NULL,
        PRIMARY KEY ([ID]),
        FOREIGN KEY ([TradingID]) REFERENCES Tradings([ID]),
        FOREIGN KEY([UserID]) REFERENCES Users([ID]),
    )