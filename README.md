# keyword
Sensitive word filtering, Text anti harmony and similarity calculation


## AC自动机
AC自动机实现字符串的过滤，时间复杂度O(n)
1. 插入敏感词
2. 构建fail指针
3. 直接查找


## 使用
注入SensitiveHelper类，然后用里面的replace方法就可以。

文字过滤的步骤是
1. 文件格式化
2. 敏感词查找
3. 敏感词替换
所以基于这个步骤，实现了可配置化：

1. 对于格式化来说，在WordContext类里面进行配置
2. 对于替换策略来说，实现ISensitiveWordReplace接口的replace接口即可

## TODO
1. 检测网站
2. 检测邮箱
3. 忽略重复词 (目前不会写，感觉时间复杂度会是O(n^2)的)
比如fffuuck这种词汇，应该被过滤掉。
