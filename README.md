# MyCalendar

## 前言

感谢 @zhairuihao 提供源码！

原来的代码和数据库结构版本对不上，无法正常运行。 

在原有基础上，我将该项目转换成了maven项目，将不能用的、与会议室预约无关的代码进行了删除，补全了数据库文件，目前已能正常运行。

## 技术选型
Java

JFinal

fullcalendar

dwz

layer

## 功能
会议室预约管理


## 使用说明
1、在mysql数据库中新建一个MyCalendar数据库，utf-8。导入resources/DBInit目录下的MyCalendar.sql文件。

2、修改数据库连接文件：jdbcConfig.properties

3、启动项目，打开127.0.0.1，使用以下账号登录：jin/123456或jie/123456。目前并没有限定谁是管理员权限，所以都可以登录预约端和管理端

