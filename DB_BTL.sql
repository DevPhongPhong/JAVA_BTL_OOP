CREATE DATABASE DB_BTL_OOP;

GO
    USE DB_BTL_OOP;

GO
    CREATE TABLE Roles (
        [ID] INT IDENTITY(1, 1),
        [Name] NVARCHAR(15) NOT NULL,
        PRIMARY KEY ([ID]),
    )
GO
    CREATE TABLE Sectors(
        [ID] INT IDENTITY(1, 1),
        [Name] NTEXT NOT NULL,
        PRIMARY KEY ([ID])
    )
GO
    CREATE TABLE Users(
        [ID] INT IDENTITY(1, 1),
        [Name] NVARCHAR(30) NOT NULL,
        -- Xác thực bằng email học viện
        [Email] NVARCHAR(30) NOT NULL UNIQUE,
        -- Khi đồng ý trao đổi sẽ hiện số điện thoại để người dùng tự trao đổi với nhau
        [PhoneNumber] NVARCHAR(10) NOT NULL,
        [RoleID] INT NOT NULL,
        [Username] NVARCHAR(30) NOT NULL UNIQUE,
        -- Hash SHA256
        [Password] NVARCHAR(64) NOT NULL,
        PRIMARY KEY ([ID]),
        FOREIGN KEY ([RoleID]) REFERENCES Roles([ID])
    )
GO
    CREATE TABLE Students(
        [UserID] INT NOT NULL,
        --MSV
        [StudentCode] NVARCHAR(10) NOT NULL,
        --Ngành dùng để đề xuất những cái swap liên quan 
        [SectorID] INT NOT NULL,
        PRIMARY KEY ([UserID]),
        FOREIGN KEY ([UserID]) REFERENCES Users([ID]),
        FOREIGN KEY ([SectorID]) REFERENCES Sectors([ID])
    )
GO
    CREATE TABLE Administrators(
        [UserID] INT NOT NULL,
        [CreatedDate] DATETIME NOT NULL,
        [CreatedByUserID] INT,
        PRIMARY KEY ([UserID]),
        FOREIGN KEY ([UserID]) REFERENCES Users([ID]),
    )
GO
    CREATE TABLE Courses(
        [ID] INT IDENTITY(1, 1),
        --Tương ứng mã môn học trong bảng đăng ký môn học của QLĐT
        [CourseCode] NVARCHAR(10) NOT NULL,
        -- Dùng để tìm kiếm vì người dùng nhớ tên môn hơn mã môn
        [CourseName] NTEXT NOT NULL,
        -- Nhóm học
        [StudyGroup] TINYINT NOT NULL,
        -- Nhóm thực hành (nếu không có thì giá trị bằng 0 )
        [PracticeGroup] TINYINT NOT NULL DEFAULT 0,
        PRIMARY KEY ([ID]),
    )
GO
    CREATE TABLE Swaps (
        [ID] INT IDENTITY(1, 1),
        [CreatedByStudentID] INT NOT NULL,
        -- ngày tạo, dùng để biết tạo sớm hay muộn để đề xuất, cũng như xóa khi hết thời hạn
        [CreatedDate] DATETIME NOT NULL,
        -- Nhóm học muốn đổi đi
        [CourseID] INT NOT NULL,
        PRIMARY KEY ([ID]),
        FOREIGN KEY([CreatedByStudentID]) REFERENCES Students([UserID]),
        FOREIGN KEY([CourseID]) REFERENCES Courses([ID])
    )
GO
    -- Bảng này lưu những môn mà người muốn nhận được
    CREATE TABLE SwapWishes (
        [ID] INT IDENTITY(1, 1),
        [SwapID] INT NOT NULL,
        [CourseID] INT NOT NULL,
        PRIMARY KEY ([ID]),
        FOREIGN KEY ([SwapID]) REFERENCES Swaps([ID]),
        FOREIGN KEY ([CourseID]) REFERENCES Courses([ID])
    )
GO
    -- Bảng này lưu những người tham gia vào 1 cái Swap nào đó
    CREATE TABLE JoinSwaps(
        [ID] INT IDENTITY(1, 1),
        -- Người tham gia
        [StudentID] INT NOT NULL,
        -- Swap tham gia
        [SwapID] INT NOT NULL,
        -- Môn đổi
        [CourseID] INT NOT NULL,
        PRIMARY KEY ([ID]),
        FOREIGN KEY ([SwapID]) REFERENCES Swaps([ID]),
        FOREIGN KEY([StudentID]) REFERENCES Students([UserID]),
        FOREIGN KEY ([CourseID]) REFERENCES Courses([ID]),
    )