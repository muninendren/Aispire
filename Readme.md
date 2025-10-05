🧠 AIspire – AI-Powered Goal Management System
🌟 Overview

AIspire is an AI-powered goal management and motivation system built with Spring Boot.
It helps users break down their goals into actionable milestones using Ollama AI (LLaMA 3 model) and automatically sends email reminders to keep them motivated and on track.

🚀 Features

Feature	Description

🧩 AI-Powered Goal Breakdown	Breaks user goals into step-by-step milestones using LLaMA 3

📬 Automated Email Reminders	Sends motivational emails for upcoming/overdue milestones


📊 Goal Management	Create, view, and track progress on personal or professional goals

⚙️ MySQL Integration	Persistent goal and milestone storage

✉️ SMTP Integration	Email reminders via Gmail App Passwords

🧠 Spring AI Integration	Uses Ollama LLaMA 3 for smart goal analysis and planning

🏗️ Tech Stack

Layer	Technology

Framework	Spring Boot 3.5.3

Language	Java 17

Database	MySQL

AI Engine	Spring AI + Ollama (LLaMA 3)

Email Service	Spring Mail (Gmail SMTP)

Build Tool	Maven

Others	JPA, Lombok, Validation API

🧩 Data Models

🎯 Goal Entity

id           : Long

title        : String


description  : String

createdDate  : LocalDate

targetDate   : LocalDate

mail         : String

milestones   : List<Milestone>

isDone       : Boolean

📅 Milestone Entity

id          : Long

description : String

dueDate     : LocalDate

completed   : boolean

goal        : Goal

👤 AppUser Entity

id     : Long

name   : String

email  : String

🔗 API Endpoints

Method	Endpoint	Description

POST	/api/goals	Create new goal and auto-generate milestones using AI

GET	/api/goals	Retrieve all goals

GET	/api/goals/{id}	Get details of a specific goal with milestones
🧠 AI Integration

Model: llama3:latest (via Ollama)

Temperature: 0 (deterministic output)

Prompt Engineering:

Acts as a personal life coach

Generates realistic, sequential milestones

Assigns achievable due dates till targetDate

🧩 Example Prompt:

“You are a personal goal coach. Break the goal ‘Learn Spring Boot in 1 month’ into actionable weekly milestones with dates.”

🧩 Example AI Response:

1. Week 1 (2025-10-10): Learn Spring Boot basics and setup project
2. Week 2 (2025-10-17): Explore JPA and MySQL integration
3. Week 3 (2025-10-24): Add Security and JWT Authentication
4. Week 4 (2025-10-31): Final Project + Deployment

📬 Email Reminder System

Configuration:

SMTP: smtp.gmail.com:587

TLS: Enabled

Authentication: App-specific password

Logic:

Runs scheduled task daily

Finds incomplete milestones

Sends motivational emails with progress context

🧠 Example Email Content:

Subject: Keep Going – You're Almost There!

Hi Muni,
Your milestone "Integrate Spring Security" for goal "Learn Spring Boot" is due tomorrow.
Stay consistent — you’re doing great!

⚙️ Configuration (application.properties)
# MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/aispire
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Ollama AI
spring.ai.ollama.model=llama3:latest

# Mail
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=yourmail@gmail.com
spring.mail.password=yourapppassword
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true

🧪 Example Postman Collection

Create Goal	POST	

/api/goals	

json 

{ "title": "Learn Spring Boot", "description": "Master Spring Boot using AI guidance", "targetDate": "2025-10-31", "mail": "muni@gmail.com" }

Get All Goals	GET	/api/goals	—

Get Goal By ID	GET	/api/goals/1	—

⚡ System Requirements
Requirement	Version
Java	17+
Maven	3.6+
MySQL	8+
Ollama	Installed locally
Gmail	App Password enabled
🧱 CI/CD Pipeline (Optional)

GitHub Actions for Maven build & test

Automatic tests on push or PR

Can be extended for deployment to AWS / Render

🎯 Real-World Use Cases
Category	Use Case

Personal	Fitness goals, learning plans, self-improvement

Professional	Project management, career growth, skill tracking
Education	Academic milestones, research timelines

Teams	Shared goal tracking & progress analysis
🚀 Future Enhancements

User Authentication & Dashboards

Dynamic AI-generated progress insights

Mobile app integration (Push Notifications)

Visual progress analytics and charts

💡 Author

Muninendren
💻 Java Developer | Spring Boot | MySQL | AI Integrations
📧 [muninendren@gmail.com
]
🌐 [https://github.com/muninendren]