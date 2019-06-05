# 作为平时 工作的脚手架 采用的框架spring-boot 

## mybatis
### pagehelper
### mybatis generator


## tomcat
### spring-mvc

## spring-aop
典型的 用户-角色-权限模型


### jjwt 

## redis
### lettuce

## rocketmq


## 使用jasypt加解密

推荐使用环境变量
　
```
vim /etc/profile
export JASYPT_PASSWORD = G0CvDz7oJn6
source /etc/profile
```

```
java -jar -Djasypt.encryptor.password=${JASYPT_PASSWORD} xxx.jar
```
