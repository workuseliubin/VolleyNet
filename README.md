# 概述：
- 这是用MVP构建的项目，网络请求封装使用的Volley

- 其中网络请求方面，因为没有合适的接口，所以随便填了一份。

# 说明：
### 1、RequestUrlUtil 类中：`HOSTURL` 需要变更为自己项目的`HostURL`
### 2、 LoginRequest类中：`.setPath("/api/login")` 需要变更为自己项目的分支URL

- 如果是Post请求需要和我一样实现下方的postValue方法传递参数。
- 如果是GET请求，请删掉postValue方法，setURL方法中使用addParam()方法添加参数。
### 3、 AuthCodeRequest（发送验证码）类及CheckPhoneRequest（校验手机号）类：根据业务需求同上。
### 4、 BaseRequest类需要根据自己的业务需求更改下方的参数封装及BaseResponse类的参数类型。
#### 其它使用上的问题或者有技术上的建议，欢迎给我留言，我会继续跟进项目。
