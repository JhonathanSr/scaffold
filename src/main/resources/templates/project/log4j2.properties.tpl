status = warn

# Console Appender
appender.console.type = Console
appender.console.name = ConsoleAppender
appender.console.target = SYSTEM_OUT
appender.console.layout.type = PatternLayout
appender.console.layout.pattern = %d{yyyy-MM-dd HH:mm:ss} [%t] %-5level %c{2} - %msg%n

# Rolling File Appender
appender.rolling.type = RollingFile
appender.rolling.name = FileAppender
appender.rolling.fileName = logs/${PROJECT_NAME}.log
appender.rolling.filePattern = logs/${PROJECT_NAME}-%d{yyyy-MM-dd}-%i.log.gz
appender.rolling.layout.type = PatternLayout
appender.rolling.layout.pattern = %d{yyyy-MM-dd HH:mm:ss} [%t] %-5level %c{2} - %msg%n
appender.rolling.policies.type = Policies
appender.rolling.policies.time.type = TimeBasedTriggeringPolicy
appender.rolling.policies.time.interval = 1
appender.rolling.policies.size.type = SizeBasedTriggeringPolicy
appender.rolling.policies.size.size = 10MB

# Root Logger
rootLogger.level = info
rootLogger.appenderRefs = console, rolling
rootLogger.appenderRef.console.ref = ConsoleAppender
rootLogger.appenderRef.rolling.ref = FileAppender