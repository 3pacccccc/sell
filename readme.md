# 采用springboot打造的微信点餐系统

##环境
JDK1.8 springboot mysql redis 
## 涉及技术
采用jpa与数据库mysql进行交互，采用redis作为缓存以及高并发下的进程锁。买家端采用
前后端分离，restful风格接口。卖家端前后端不分离，ModelAndView后端渲染前端模板。
涉及到微信登录、微信支付、微信获取openid,accessToken等相关接口