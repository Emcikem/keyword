# keyword
Sensitive word filtering, Text anti harmony and similarity calculation


## AC自动机
AC自动机实现字符串的过滤，时间复杂度O(n)
1. 插入敏感词
2. 构建fail指针
3. 直接查找


## 使用
注入SensitiveHelper类，然后用里面的replace方法就可以。

自定义实现


## TODO
1. 检测网站
2. 检测邮箱
3. 忽略重复词 (目前不会写，感觉时间复杂度会是O(n^2)的)
比如fffuuck这种词汇，应该被过滤掉。
