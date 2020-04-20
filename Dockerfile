FROM openjdk:8-jdk-alpine
RUN addgroup -S orderapp && adduser -S orderapp -G orderapp
USER orderapp:orderapp
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","com.classpath.ordermgmt.OrderMgmtApplication"]