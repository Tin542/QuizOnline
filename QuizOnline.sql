USE [master]
GO
/****** Object:  Database [QuizOnline]    Script Date: 4/16/2021 8:11:10 PM ******/
CREATE DATABASE [QuizOnline] ON  PRIMARY 
( NAME = N'QuizOnline', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\QuizOnline.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'QuizOnline_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\QuizOnline_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [QuizOnline].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [QuizOnline] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [QuizOnline] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [QuizOnline] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [QuizOnline] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [QuizOnline] SET ARITHABORT OFF 
GO
ALTER DATABASE [QuizOnline] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [QuizOnline] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [QuizOnline] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [QuizOnline] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [QuizOnline] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [QuizOnline] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [QuizOnline] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [QuizOnline] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [QuizOnline] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [QuizOnline] SET  DISABLE_BROKER 
GO
ALTER DATABASE [QuizOnline] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [QuizOnline] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [QuizOnline] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [QuizOnline] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [QuizOnline] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [QuizOnline] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [QuizOnline] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [QuizOnline] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [QuizOnline] SET  MULTI_USER 
GO
ALTER DATABASE [QuizOnline] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [QuizOnline] SET DB_CHAINING OFF 
GO
USE [QuizOnline]
GO
/****** Object:  User [Tin]    Script Date: 4/16/2021 8:11:10 PM ******/
CREATE USER [Tin] FOR LOGIN [Tin] WITH DEFAULT_SCHEMA=[dbo]
GO
/****** Object:  Table [dbo].[tblAnswer]    Script Date: 4/16/2021 8:11:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblAnswer](
	[answer_id] [varchar](10) NOT NULL,
	[answer_content] [nvarchar](max) NOT NULL,
	[type] [bit] NOT NULL,
	[question_id] [varchar](10) NOT NULL,
 CONSTRAINT [PK_tblAnswer] PRIMARY KEY CLUSTERED 
(
	[answer_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblQuestion]    Script Date: 4/16/2021 8:11:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblQuestion](
	[question_id] [varchar](10) NOT NULL,
	[question_content] [nvarchar](max) NOT NULL,
	[answer_content] [nvarchar](max) NOT NULL,
	[answer_correct] [varchar](max) NOT NULL,
	[createDate] [date] NOT NULL,
	[subjectID] [varchar](15) NOT NULL,
	[statusID] [nchar](5) NOT NULL,
 CONSTRAINT [PK_tblQuestion] PRIMARY KEY CLUSTERED 
(
	[question_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblResult]    Script Date: 4/16/2021 8:11:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblResult](
	[resultID] [nvarchar](50) NOT NULL,
	[email] [nvarchar](255) NOT NULL,
	[subjectID] [varchar](15) NOT NULL,
	[name] [nvarchar](50) NOT NULL,
	[grade] [float] NOT NULL,
	[noOfCorrect] [int] NOT NULL,
	[dateOfCreate] [datetime] NOT NULL,
 CONSTRAINT [PK_tblResult_1] PRIMARY KEY CLUSTERED 
(
	[resultID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblStatus]    Script Date: 4/16/2021 8:11:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblStatus](
	[statusID] [nchar](5) NOT NULL,
	[statusName] [varchar](50) NOT NULL,
 CONSTRAINT [PK_tblStatus] PRIMARY KEY CLUSTERED 
(
	[statusID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblSubject]    Script Date: 4/16/2021 8:11:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblSubject](
	[subjectID] [varchar](15) NOT NULL,
	[subjectName] [nvarchar](50) NOT NULL,
	[time] [int] NULL,
	[noOfQuestion] [int] NULL,
 CONSTRAINT [PK_tblSubject] PRIMARY KEY CLUSTERED 
(
	[subjectID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblUser]    Script Date: 4/16/2021 8:11:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblUser](
	[email] [nvarchar](255) NOT NULL,
	[password] [varchar](64) NOT NULL,
	[name] [nvarchar](50) NOT NULL,
	[role] [varchar](20) NOT NULL,
	[statusID] [nchar](5) NOT NULL,
 CONSTRAINT [PK_tblUser] PRIMARY KEY CLUSTERED 
(
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q1-1', N'It is an XML document.', 0, N'Q1')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q1-2', N'It cannot be unpackaged by the container.', 0, N'Q1')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q1-3', N'It is used by web application developer to deliver the web application in a single unit.', 0, N'Q1')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q1-4', N'It contains web components such as servlets as well as EJBs.', 1, N'Q1')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q10-1', N'declaration', 1, N'Q10')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q10-2', N'scriptlet', 0, N'Q10')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q10-3', N'expression', 0, N'Q10')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q10-4', N'page', 0, N'Q10')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q11-1', N'5', 1, N'Q11')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q11-2', N'3', 0, N'Q11')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q11-3', N'4', 0, N'Q11')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q11-4', N'1', 0, N'Q11')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q12-1', N'2', 1, N'Q12')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q12-2', N'4', 0, N'Q12')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q12-3', N'42', 0, N'Q12')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q12-4', N'43', 0, N'Q12')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q13-1', N'50', 1, N'Q13')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q13-2', N'23', 0, N'Q13')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q13-3', N'34', 0, N'Q13')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q13-4', N'346', 0, N'Q13')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q14-1', N'34', 0, N'Q14')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q14-2', N'20', 1, N'Q14')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q14-3', N'435', 0, N'Q14')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q14-4', N'56', 0, N'Q14')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q15-1', N'345', 0, N'Q15')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q15-2', N'54', 0, N'Q15')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q15-3', N'56', 0, N'Q15')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q15-4', N'10', 1, N'Q15')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q16-1', N'Database', 1, N'Q16')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q16-2', N'Database Instance', 0, N'Q16')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q16-3', N'Schema', 0, N'Q16')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q16-4', N'Schema Instance', 0, N'Q16')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q17-1', N'Database is created and maintained by a DMBS', 0, N'Q17')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q17-2', N'All of the others', 1, N'Q17')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q17-3', N'Schema', 0, N'Q17')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q17-4', N'Schema Instance', 0, N'Q17')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q18-1', N'EVAL_PAGE', 0, N'Q18')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q18-2', N'EVAL_BODY', 0, N'Q18')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q18-3', N'EVAL_PAGE_AGAIN', 0, N'Q18')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q18-4', N'SKIP_BODY', 1, N'Q18')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q19-1', N'Tag', 1, N'Q19')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q19-2', N'Description', 0, N'Q19')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q19-3', N'Validator', 0, N'Q19')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q19-4', N'Name', 0, N'Q19')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q2-1', N'Cookie', 1, N'Q2')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q2-2', N'Session', 0, N'Q2')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q2-3', N'Request', 0, N'Q2')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q2-4', N'Response', 0, N'Q2')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q20-1', N'Tag', 1, N'Q20')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q20-2', N'Description', 0, N'Q20')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q20-3', N'Validator', 0, N'Q20')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q20-4', N'Name', 0, N'Q20')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q21-1', N'Tag', 1, N'Q21')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q21-2', N'Description', 0, N'Q21')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q21-3', N'Validator', 0, N'Q21')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q21-4', N'Name', 0, N'Q21')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q22-1', N'2', 0, N'Q22')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q22-2', N'3', 0, N'Q22')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q22-3', N'32', 0, N'Q22')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q22-4', N'1', 1, N'Q22')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q23-1', N'Association', 0, N'Q23')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q23-2', N'Aggregation', 0, N'Q23')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q23-3', N'Composition', 1, N'Q23')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q23-4', N'Inheritance', 0, N'Q23')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q24-1', N'A. Association', 1, N'Q24')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q24-2', N'B. Aggregation', 0, N'Q24')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q24-3', N'C. Composition', 0, N'Q24')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q24-4', N'D. Inheritance', 0, N'Q24')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q25-1', N'frame.setLocation(100, 100)', 0, N'Q25')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q25-2', N'frame.setLocation(100, 200)', 0, N'Q25')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q25-3', N'frame.setLocation(200, 100)', 1, N'Q25')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q25-4', N' frame.setLocation(200, 200)', 0, N'Q25')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q3-1', N'blank', 0, N'Q3')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q3-2', N'empty', 1, N'Q3')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q3-3', N'null', 0, N'Q3')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q3-4', N'false', 0, N'Q3')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q4-1', N'AWT components', 1, N'Q4')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q4-2', N'Swing components', 0, N'Q4')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q4-3', N'GUI components', 0, N'Q4')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q4-4', N'Non-GUI components', 0, N'Q4')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q5-1', N'JButton', 0, N'Q5')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q5-2', N'JTextField', 0, N'Q5')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q5-3', N'JPanel', 0, N'Q5')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q5-4', N'JFrame', 1, N'Q5')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q50-1', N'5', 0, N'Q50')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q50-2', N'9 ', 0, N'Q50')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q50-3', N'6', 1, N'Q50')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q50-4', N'4', 0, N'Q50')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q6-1', N'3', 0, N'Q6')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q6-2', N'2', 0, N'Q6')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q6-3', N'1', 0, N'Q6')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q6-4', N'5', 1, N'Q6')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q7-1', N'software', 1, N'Q7')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q7-2', N'game', 0, N'Q7')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q7-3', N'computer', 0, N'Q7')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q7-4', N'compiler', 0, N'Q7')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q8-1', N'5', 0, N'Q8')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q8-2', N'2', 0, N'Q8')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q8-3', N'4', 1, N'Q8')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q8-4', N'1', 0, N'Q8')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q9-1', N'A status of 200 to 299 signifies that the request was successful.', 1, N'Q9')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q9-2', N'A status of 300 to 399 is informational messages.', 0, N'Q9')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q9-3', N'A status of 400 to 499 indicates an error in the server', 0, N'Q9')
GO
INSERT [dbo].[tblAnswer] ([answer_id], [answer_content], [type], [question_id]) VALUES (N'Q9-4', N'A status of 500 to 599 indicates an error in the client.', 0, N'Q9')
GO
INSERT [dbo].[tblQuestion] ([question_id], [question_content], [answer_content], [answer_correct], [createDate], [subjectID], [statusID]) VALUES (N'Q1', N'Identify correct statement about a WAR file.(Choose one)', N'asd', N'It contains web components such as servlets as well as EJBs.', CAST(N'2021-02-26' AS Date), N'DBI201', N'S001 ')
GO
INSERT [dbo].[tblQuestion] ([question_id], [question_content], [answer_content], [answer_correct], [createDate], [subjectID], [statusID]) VALUES (N'Q10', N'A JSP ________________ lets you define methods or fields that get inserted into the main body of the servlet class (outside of the _jspService method that is called by service to process the request).', N'efsf', N'declaration', CAST(N'2021-02-26' AS Date), N'DBI201', N'S001 ')
GO
INSERT [dbo].[tblQuestion] ([question_id], [question_content], [answer_content], [answer_correct], [createDate], [subjectID], [statusID]) VALUES (N'Q11', N'3 + 3', N'sadasd', N'5', CAST(N'2021-02-10' AS Date), N'MAD201', N'S001 ')
GO
INSERT [dbo].[tblQuestion] ([question_id], [question_content], [answer_content], [answer_correct], [createDate], [subjectID], [statusID]) VALUES (N'Q12', N'1+1', N'sad', N'2', CAST(N'2021-02-18' AS Date), N'MAD201', N'S001 ')
GO
INSERT [dbo].[tblQuestion] ([question_id], [question_content], [answer_content], [answer_correct], [createDate], [subjectID], [statusID]) VALUES (N'Q13', N'15+35', N'asd', N'50', CAST(N'2021-02-18' AS Date), N'MAD201', N'S001 ')
GO
INSERT [dbo].[tblQuestion] ([question_id], [question_content], [answer_content], [answer_correct], [createDate], [subjectID], [statusID]) VALUES (N'Q14', N'10+10', N'asd', N'20', CAST(N'2021-02-18' AS Date), N'MAD201', N'S001 ')
GO
INSERT [dbo].[tblQuestion] ([question_id], [question_content], [answer_content], [answer_correct], [createDate], [subjectID], [statusID]) VALUES (N'Q15', N'5+5', N'sd', N'10', CAST(N'2021-02-18' AS Date), N'MAD201', N'S001 ')
GO
INSERT [dbo].[tblQuestion] ([question_id], [question_content], [answer_content], [answer_correct], [createDate], [subjectID], [statusID]) VALUES (N'Q16', N'A ____ is a logically coherent collection of data with some inherent meaning, representing some aspect of real world and being designed, built and populated with data for a specific purpose', N'sada', N'Database', CAST(N'2021-02-19' AS Date), N'DBI201', N'S001 ')
GO
INSERT [dbo].[tblQuestion] ([question_id], [question_content], [answer_content], [answer_correct], [createDate], [subjectID], [statusID]) VALUES (N'Q17', N'Choose the most correct statement.', N'sada', N'All of the others', CAST(N'2021-02-18' AS Date), N'DBI201', N'S001 ')
GO
INSERT [dbo].[tblQuestion] ([question_id], [question_content], [answer_content], [answer_correct], [createDate], [subjectID], [statusID]) VALUES (N'Q18', N'Which of these is legal return types of the doAfterBody method defined in a class that extends TagSupport class?.', N'asdad', N'SKIP_BODY', CAST(N'2021-02-24' AS Date), N'PRJ321', N'S001 ')
GO
INSERT [dbo].[tblQuestion] ([question_id], [question_content], [answer_content], [answer_correct], [createDate], [subjectID], [statusID]) VALUES (N'Q19', N'Which element defined within the taglib element of taglib descriptor file is required? Select one correct answer.', N'asdaf', N'Tag', CAST(N'2021-03-01' AS Date), N'DBI201', N'S001 ')
GO
INSERT [dbo].[tblQuestion] ([question_id], [question_content], [answer_content], [answer_correct], [createDate], [subjectID], [statusID]) VALUES (N'Q2', N'A ________has a name, a single value, and optional attributes such as a comment, path and domain qualifiers, a maximum age, and a version number.', N'asda', N'Cookie', CAST(N'2021-02-26' AS Date), N'PRJ321', N'S001 ')
GO
INSERT [dbo].[tblQuestion] ([question_id], [question_content], [answer_content], [answer_correct], [createDate], [subjectID], [statusID]) VALUES (N'Q20', N'Which element defined within the taglib element of taglib descriptor file is required? Select one correct answer.', N'asdaf', N'Tag', CAST(N'2021-02-24' AS Date), N'PRJ321', N'S003 ')
GO
INSERT [dbo].[tblQuestion] ([question_id], [question_content], [answer_content], [answer_correct], [createDate], [subjectID], [statusID]) VALUES (N'Q21', N'Which element defined within the taglib element of taglib descriptor file is required? Select one correct answer.', N'asdaf', N'Tag', CAST(N'2021-02-24' AS Date), N'PRJ311', N'S001 ')
GO
INSERT [dbo].[tblQuestion] ([question_id], [question_content], [answer_content], [answer_correct], [createDate], [subjectID], [statusID]) VALUES (N'Q22', N'1+1', N'ad', N'1', CAST(N'2021-02-24' AS Date), N'DBI201', N'S001 ')
GO
INSERT [dbo].[tblQuestion] ([question_id], [question_content], [answer_content], [answer_correct], [createDate], [subjectID], [statusID]) VALUES (N'Q23', N'What is best to describe the relationship between a container and a SWing GUI object in the container?', N'asdaf', N'Composition', CAST(N'2021-02-26' AS Date), N'PRJ311', N'S001 ')
GO
INSERT [dbo].[tblQuestion] ([question_id], [question_content], [answer_content], [answer_correct], [createDate], [subjectID], [statusID]) VALUES (N'Q24', N'What is best to describe the relationship between Component and Color?', N'asd', N'A. Association', CAST(N'2021-02-26' AS Date), N'PRJ311', N'S001 ')
GO
INSERT [dbo].[tblQuestion] ([question_id], [question_content], [answer_content], [answer_correct], [createDate], [subjectID], [statusID]) VALUES (N'Q25', N'Which of the following statements is for placing the frame''s upper left corner to (200, 100)?', N'sasc', N'frame.setLocation(200, 100)', CAST(N'2021-02-26' AS Date), N'PRJ311', N'S001 ')
GO
INSERT [dbo].[tblQuestion] ([question_id], [question_content], [answer_content], [answer_correct], [createDate], [subjectID], [statusID]) VALUES (N'Q3', N'What should be the value of <body-content> subelement of element <tag> in a TLD file if the tag should not have any contents as its body?', N'asda', N'empty', CAST(N'2021-02-18' AS Date), N'PRJ321', N'S003 ')
GO
INSERT [dbo].[tblQuestion] ([question_id], [question_content], [answer_content], [answer_correct], [createDate], [subjectID], [statusID]) VALUES (N'Q4', N'__________ are referred to as heavyweight components.', N'qqq', N'a', CAST(N'2021-03-08' AS Date), N'PRJ311', N'S003 ')
GO
INSERT [dbo].[tblQuestion] ([question_id], [question_content], [answer_content], [answer_correct], [createDate], [subjectID], [statusID]) VALUES (N'Q5', N'Which of the following classes is a heavyweight component?', N'sada', N'JFrame', CAST(N'2021-02-26' AS Date), N'PRJ311', N'S001 ')
GO
INSERT [dbo].[tblQuestion] ([question_id], [question_content], [answer_content], [answer_correct], [createDate], [subjectID], [statusID]) VALUES (N'Q50', N'3 + 3', N'asdaf', N'6', CAST(N'2021-03-08' AS Date), N'DBI201', N'S001 ')
GO
INSERT [dbo].[tblQuestion] ([question_id], [question_content], [answer_content], [answer_correct], [createDate], [subjectID], [statusID]) VALUES (N'Q6', N'1 + 4', N'asd', N'8', CAST(N'2021-03-01' AS Date), N'MAD201', N'S001 ')
GO
INSERT [dbo].[tblQuestion] ([question_id], [question_content], [answer_content], [answer_correct], [createDate], [subjectID], [statusID]) VALUES (N'Q7', N'Container is__', N'sd', N'software', CAST(N'2021-02-26' AS Date), N'PRJ321', N'S001 ')
GO
INSERT [dbo].[tblQuestion] ([question_id], [question_content], [answer_content], [answer_correct], [createDate], [subjectID], [statusID]) VALUES (N'Q8', N'2 x 2', N'asd', N'4', CAST(N'2021-02-10' AS Date), N'MAD201', N'S001 ')
GO
INSERT [dbo].[tblQuestion] ([question_id], [question_content], [answer_content], [answer_correct], [createDate], [subjectID], [statusID]) VALUES (N'Q9', N'Which of the following statements are correct about the status of the Http response? Select the one correct answer', N'asd', N'A status of 200 to 299 signifies that the request was successful.', CAST(N'2021-02-26' AS Date), N'PRJ321', N'S001 ')
GO
INSERT [dbo].[tblResult] ([resultID], [email], [subjectID], [name], [grade], [noOfCorrect], [dateOfCreate]) VALUES (N'Bim@gmail.com-1', N'Bim@gmail.com', N'PRJ311', N'admin', 0, 0, CAST(N'2021-02-18T14:14:22.000' AS DateTime))
GO
INSERT [dbo].[tblResult] ([resultID], [email], [subjectID], [name], [grade], [noOfCorrect], [dateOfCreate]) VALUES (N'Bim@gmail.com-10', N'Bim@gmail.com', N'PRJ321', N'admin', 0, 0, CAST(N'2021-03-02T10:35:58.000' AS DateTime))
GO
INSERT [dbo].[tblResult] ([resultID], [email], [subjectID], [name], [grade], [noOfCorrect], [dateOfCreate]) VALUES (N'Bim@gmail.com-11', N'Bim@gmail.com', N'MAD201', N'admin', 2, 1, CAST(N'2021-03-05T13:03:25.000' AS DateTime))
GO
INSERT [dbo].[tblResult] ([resultID], [email], [subjectID], [name], [grade], [noOfCorrect], [dateOfCreate]) VALUES (N'Bim@gmail.com-2', N'Bim@gmail.com', N'PRJ321', N'admin', 3, 1, CAST(N'2021-02-18T14:14:41.000' AS DateTime))
GO
INSERT [dbo].[tblResult] ([resultID], [email], [subjectID], [name], [grade], [noOfCorrect], [dateOfCreate]) VALUES (N'Bim@gmail.com-3', N'Bim@gmail.com', N'PRJ321', N'admin', 6, 2, CAST(N'2021-02-18T14:14:47.000' AS DateTime))
GO
INSERT [dbo].[tblResult] ([resultID], [email], [subjectID], [name], [grade], [noOfCorrect], [dateOfCreate]) VALUES (N'Bim@gmail.com-4', N'Bim@gmail.com', N'MAD201', N'admin', 8, 4, CAST(N'2021-02-26T03:03:22.000' AS DateTime))
GO
INSERT [dbo].[tblResult] ([resultID], [email], [subjectID], [name], [grade], [noOfCorrect], [dateOfCreate]) VALUES (N'Bim@gmail.com-5', N'Bim@gmail.com', N'PRJ321', N'admin', 4, 2, CAST(N'2021-02-26T03:24:12.000' AS DateTime))
GO
INSERT [dbo].[tblResult] ([resultID], [email], [subjectID], [name], [grade], [noOfCorrect], [dateOfCreate]) VALUES (N'Bim@gmail.com-6', N'Bim@gmail.com', N'MAD201', N'admin', 4, 2, CAST(N'2021-03-01T17:39:16.000' AS DateTime))
GO
INSERT [dbo].[tblResult] ([resultID], [email], [subjectID], [name], [grade], [noOfCorrect], [dateOfCreate]) VALUES (N'Bim@gmail.com-7', N'Bim@gmail.com', N'DBI201', N'admin', 0, 0, CAST(N'2021-03-02T09:28:40.000' AS DateTime))
GO
INSERT [dbo].[tblResult] ([resultID], [email], [subjectID], [name], [grade], [noOfCorrect], [dateOfCreate]) VALUES (N'Bim@gmail.com-8', N'Bim@gmail.com', N'DBI201', N'admin', 2, 1, CAST(N'2021-03-02T09:35:56.000' AS DateTime))
GO
INSERT [dbo].[tblResult] ([resultID], [email], [subjectID], [name], [grade], [noOfCorrect], [dateOfCreate]) VALUES (N'Bim@gmail.com-9', N'Bim@gmail.com', N'DBI201', N'admin', 6, 3, CAST(N'2021-03-02T09:37:34.000' AS DateTime))
GO
INSERT [dbo].[tblResult] ([resultID], [email], [subjectID], [name], [grade], [noOfCorrect], [dateOfCreate]) VALUES (N'Tin@gmail.com-1', N'Tin@gmail.com', N'PRJ311', N'Tin', 6, 2, CAST(N'2021-02-18T12:39:01.000' AS DateTime))
GO
INSERT [dbo].[tblResult] ([resultID], [email], [subjectID], [name], [grade], [noOfCorrect], [dateOfCreate]) VALUES (N'Tin@gmail.com-10', N'Tin@gmail.com', N'MAD201', N'Tin', 3, 1, CAST(N'2021-02-19T17:18:04.000' AS DateTime))
GO
INSERT [dbo].[tblResult] ([resultID], [email], [subjectID], [name], [grade], [noOfCorrect], [dateOfCreate]) VALUES (N'Tin@gmail.com-11', N'Tin@gmail.com', N'MAD201', N'Tin', 3, 1, CAST(N'2021-02-19T17:22:00.000' AS DateTime))
GO
INSERT [dbo].[tblResult] ([resultID], [email], [subjectID], [name], [grade], [noOfCorrect], [dateOfCreate]) VALUES (N'Tin@gmail.com-12', N'Tin@gmail.com', N'MAD201', N'Tin', 3, 1, CAST(N'2021-02-19T17:26:56.000' AS DateTime))
GO
INSERT [dbo].[tblResult] ([resultID], [email], [subjectID], [name], [grade], [noOfCorrect], [dateOfCreate]) VALUES (N'Tin@gmail.com-13', N'Tin@gmail.com', N'DBI201', N'Tin', 0, 0, CAST(N'2021-02-25T21:13:30.000' AS DateTime))
GO
INSERT [dbo].[tblResult] ([resultID], [email], [subjectID], [name], [grade], [noOfCorrect], [dateOfCreate]) VALUES (N'Tin@gmail.com-14', N'Tin@gmail.com', N'DBI201', N'Tin', 0, 0, CAST(N'2021-02-25T21:32:22.000' AS DateTime))
GO
INSERT [dbo].[tblResult] ([resultID], [email], [subjectID], [name], [grade], [noOfCorrect], [dateOfCreate]) VALUES (N'Tin@gmail.com-15', N'Tin@gmail.com', N'DBI201', N'Tin', 0, 0, CAST(N'2021-02-25T21:42:20.000' AS DateTime))
GO
INSERT [dbo].[tblResult] ([resultID], [email], [subjectID], [name], [grade], [noOfCorrect], [dateOfCreate]) VALUES (N'Tin@gmail.com-16', N'Tin@gmail.com', N'MAD201', N'Tin', 0, 0, CAST(N'2021-02-26T01:22:26.000' AS DateTime))
GO
INSERT [dbo].[tblResult] ([resultID], [email], [subjectID], [name], [grade], [noOfCorrect], [dateOfCreate]) VALUES (N'Tin@gmail.com-17', N'Tin@gmail.com', N'MAD201', N'Tin', 0, 0, CAST(N'2021-02-26T02:09:30.000' AS DateTime))
GO
INSERT [dbo].[tblResult] ([resultID], [email], [subjectID], [name], [grade], [noOfCorrect], [dateOfCreate]) VALUES (N'Tin@gmail.com-18', N'Tin@gmail.com', N'MAD201', N'Tin', 0, 0, CAST(N'2021-02-26T02:24:24.000' AS DateTime))
GO
INSERT [dbo].[tblResult] ([resultID], [email], [subjectID], [name], [grade], [noOfCorrect], [dateOfCreate]) VALUES (N'Tin@gmail.com-19', N'Tin@gmail.com', N'MAD201', N'Tin', 0, 0, CAST(N'2021-02-26T02:38:17.000' AS DateTime))
GO
INSERT [dbo].[tblResult] ([resultID], [email], [subjectID], [name], [grade], [noOfCorrect], [dateOfCreate]) VALUES (N'Tin@gmail.com-2', N'Tin@gmail.com', N'PRJ311', N'Tin', 6, 2, CAST(N'2021-02-18T12:39:10.000' AS DateTime))
GO
INSERT [dbo].[tblResult] ([resultID], [email], [subjectID], [name], [grade], [noOfCorrect], [dateOfCreate]) VALUES (N'Tin@gmail.com-20', N'Tin@gmail.com', N'MAD201', N'Tin', 0, 0, CAST(N'2021-02-26T02:41:47.000' AS DateTime))
GO
INSERT [dbo].[tblResult] ([resultID], [email], [subjectID], [name], [grade], [noOfCorrect], [dateOfCreate]) VALUES (N'Tin@gmail.com-21', N'Tin@gmail.com', N'MAD201', N'Tin', 0, 0, CAST(N'2021-02-26T02:42:33.000' AS DateTime))
GO
INSERT [dbo].[tblResult] ([resultID], [email], [subjectID], [name], [grade], [noOfCorrect], [dateOfCreate]) VALUES (N'Tin@gmail.com-22', N'Tin@gmail.com', N'MAD201', N'Tin', 6, 3, CAST(N'2021-03-08T10:32:22.000' AS DateTime))
GO
INSERT [dbo].[tblResult] ([resultID], [email], [subjectID], [name], [grade], [noOfCorrect], [dateOfCreate]) VALUES (N'Tin@gmail.com-3', N'Tin@gmail.com', N'PRJ321', N'Tin', 6, 2, CAST(N'2021-02-18T12:39:16.000' AS DateTime))
GO
INSERT [dbo].[tblResult] ([resultID], [email], [subjectID], [name], [grade], [noOfCorrect], [dateOfCreate]) VALUES (N'Tin@gmail.com-4', N'Tin@gmail.com', N'PRJ321', N'Tin', 10, 3, CAST(N'2021-02-18T12:39:25.000' AS DateTime))
GO
INSERT [dbo].[tblResult] ([resultID], [email], [subjectID], [name], [grade], [noOfCorrect], [dateOfCreate]) VALUES (N'Tin@gmail.com-5', N'Tin@gmail.com', N'PRJ321', N'Tin', 3, 1, CAST(N'2021-02-18T12:39:48.000' AS DateTime))
GO
INSERT [dbo].[tblResult] ([resultID], [email], [subjectID], [name], [grade], [noOfCorrect], [dateOfCreate]) VALUES (N'Tin@gmail.com-6', N'Tin@gmail.com', N'MAD201', N'Tin', 3, 1, CAST(N'2021-02-19T17:13:14.000' AS DateTime))
GO
INSERT [dbo].[tblResult] ([resultID], [email], [subjectID], [name], [grade], [noOfCorrect], [dateOfCreate]) VALUES (N'Tin@gmail.com-7', N'Tin@gmail.com', N'MAD201', N'Tin', 3, 1, CAST(N'2021-02-19T17:14:02.000' AS DateTime))
GO
INSERT [dbo].[tblResult] ([resultID], [email], [subjectID], [name], [grade], [noOfCorrect], [dateOfCreate]) VALUES (N'Tin@gmail.com-8', N'Tin@gmail.com', N'MAD201', N'Tin', 3, 1, CAST(N'2021-02-19T17:16:34.000' AS DateTime))
GO
INSERT [dbo].[tblResult] ([resultID], [email], [subjectID], [name], [grade], [noOfCorrect], [dateOfCreate]) VALUES (N'Tin@gmail.com-9', N'Tin@gmail.com', N'MAD201', N'Tin', 3, 1, CAST(N'2021-02-19T17:17:40.000' AS DateTime))
GO
INSERT [dbo].[tblResult] ([resultID], [email], [subjectID], [name], [grade], [noOfCorrect], [dateOfCreate]) VALUES (N'tinntse140668@fpt.edu.vn-1', N'tinntse140668@fpt.edu.vn', N'MAD201', N'tinntse140668', 6, 3, CAST(N'2021-03-09T16:06:13.000' AS DateTime))
GO
INSERT [dbo].[tblResult] ([resultID], [email], [subjectID], [name], [grade], [noOfCorrect], [dateOfCreate]) VALUES (N'tinntse140668@fpt.edu.vn-2', N'tinntse140668@fpt.edu.vn', N'MAD201', N'tinntse140668', 0, 0, CAST(N'2021-03-09T16:06:20.000' AS DateTime))
GO
INSERT [dbo].[tblStatus] ([statusID], [statusName]) VALUES (N'S001 ', N'Active')
GO
INSERT [dbo].[tblStatus] ([statusID], [statusName]) VALUES (N'S002 ', N'New')
GO
INSERT [dbo].[tblStatus] ([statusID], [statusName]) VALUES (N'S003 ', N'Deactive')
GO
INSERT [dbo].[tblSubject] ([subjectID], [subjectName], [time], [noOfQuestion]) VALUES (N'DBI201', N'Database Instroduction', 5, 5)
GO
INSERT [dbo].[tblSubject] ([subjectID], [subjectName], [time], [noOfQuestion]) VALUES (N'MAD201', N'Math', 5, 5)
GO
INSERT [dbo].[tblSubject] ([subjectID], [subjectName], [time], [noOfQuestion]) VALUES (N'PRJ311', N'Java Desktop', 5, 5)
GO
INSERT [dbo].[tblSubject] ([subjectID], [subjectName], [time], [noOfQuestion]) VALUES (N'PRJ321', N'Java Web', 5, 5)
GO
INSERT [dbo].[tblSubject] ([subjectID], [subjectName], [time], [noOfQuestion]) VALUES (N'SWR', N'Require', 10, 20)
GO
INSERT [dbo].[tblSubject] ([subjectID], [subjectName], [time], [noOfQuestion]) VALUES (N'SWT', N'Software Testing', 5, 5)
GO
INSERT [dbo].[tblUser] ([email], [password], [name], [role], [statusID]) VALUES (N'aaadmin@fpt.edu.vn', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', N'tin', N'Student', N'S002 ')
GO
INSERT [dbo].[tblUser] ([email], [password], [name], [role], [statusID]) VALUES (N'aadmin@fpt.edu.vn', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', N'admin', N'Student', N'S002 ')
GO
INSERT [dbo].[tblUser] ([email], [password], [name], [role], [statusID]) VALUES (N'abc@gmail.com', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', N'Thanh Tin', N'Admin', N'S001 ')
GO
INSERT [dbo].[tblUser] ([email], [password], [name], [role], [statusID]) VALUES (N'admin@fpt.edu.vn', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', N'admin', N'Admin', N'S001 ')
GO
INSERT [dbo].[tblUser] ([email], [password], [name], [role], [statusID]) VALUES (N'Bim@gmail.com', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', N'admin', N'Student', N'S001 ')
GO
INSERT [dbo].[tblUser] ([email], [password], [name], [role], [statusID]) VALUES (N'HoaDNT@fpt.edu.vn', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', N'Hoa', N'Student', N'S001 ')
GO
INSERT [dbo].[tblUser] ([email], [password], [name], [role], [statusID]) VALUES (N'Tin@gmail.com', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', N'Tin', N'Student', N'S001 ')
GO
INSERT [dbo].[tblUser] ([email], [password], [name], [role], [statusID]) VALUES (N'tin123456bim@gmail.com', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', N'Tin Nguyen', N'Student', N'S001 ')
GO
INSERT [dbo].[tblUser] ([email], [password], [name], [role], [statusID]) VALUES (N'tinntse140668@fpt.edu.vn', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', N'tinntse140668', N'Student', N'S001 ')
GO
ALTER TABLE [dbo].[tblAnswer]  WITH CHECK ADD  CONSTRAINT [FK_tblAnswer_tblQuestion] FOREIGN KEY([question_id])
REFERENCES [dbo].[tblQuestion] ([question_id])
GO
ALTER TABLE [dbo].[tblAnswer] CHECK CONSTRAINT [FK_tblAnswer_tblQuestion]
GO
ALTER TABLE [dbo].[tblQuestion]  WITH CHECK ADD  CONSTRAINT [FK_tblQuestion_tblStatus] FOREIGN KEY([statusID])
REFERENCES [dbo].[tblStatus] ([statusID])
GO
ALTER TABLE [dbo].[tblQuestion] CHECK CONSTRAINT [FK_tblQuestion_tblStatus]
GO
ALTER TABLE [dbo].[tblQuestion]  WITH CHECK ADD  CONSTRAINT [FK_tblQuestion_tblSubject] FOREIGN KEY([subjectID])
REFERENCES [dbo].[tblSubject] ([subjectID])
GO
ALTER TABLE [dbo].[tblQuestion] CHECK CONSTRAINT [FK_tblQuestion_tblSubject]
GO
ALTER TABLE [dbo].[tblResult]  WITH CHECK ADD  CONSTRAINT [FK_tblResult_tblSubject] FOREIGN KEY([subjectID])
REFERENCES [dbo].[tblSubject] ([subjectID])
GO
ALTER TABLE [dbo].[tblResult] CHECK CONSTRAINT [FK_tblResult_tblSubject]
GO
ALTER TABLE [dbo].[tblResult]  WITH CHECK ADD  CONSTRAINT [FK_tblResult_tblUser] FOREIGN KEY([email])
REFERENCES [dbo].[tblUser] ([email])
GO
ALTER TABLE [dbo].[tblResult] CHECK CONSTRAINT [FK_tblResult_tblUser]
GO
ALTER TABLE [dbo].[tblUser]  WITH CHECK ADD  CONSTRAINT [FK_tblUser_tblStatus] FOREIGN KEY([statusID])
REFERENCES [dbo].[tblStatus] ([statusID])
GO
ALTER TABLE [dbo].[tblUser] CHECK CONSTRAINT [FK_tblUser_tblStatus]
GO
USE [master]
GO
ALTER DATABASE [QuizOnline] SET  READ_WRITE 
GO
