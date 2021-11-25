# electricity-bill-workspace


edit application.properties file
---------------------------------
spring.application.name=authentication-service
server.port=7100

spring.mail.properties.mail.smtp.ssl.enable = true
spring.mail.username= electricitybillpayment99@gmail.com
spring.mail.password= ldhvksjbtxollurg
spring.mail.host=smtp.gmail.com
spring.mail.properties.mail.smtp.auth=true


#mysqldb connection----------------------------------------------
spring.datasource.url=jdbc:mysql://localhost:3306/javaapp
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.datasource.username=root #username (default)
spring.datasource.password=toor #password
spring.datasource.platform=mysql
#spring.datasource.initialization-mode=always
spring.jpa.hibernate.ddl-auto=update
spring.jackson.serialization.fail-on-empty-beans=false
spring.jpa.show-sql=true


# App Properties
ebs.app.jwtSecret= EBSSecretKey
ebs.app.jwtExpirationMs= 86400000
