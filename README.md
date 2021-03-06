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
1. 文字格式化
2. 敏感词查找
3. 敏感词替换
所以基于这个步骤，实现了可配置化：

1. 对于格式化来说，在WordContext类里面进行配置
2. 对于替换策略来说，实现ISensitiveWordReplace接口的replace接口即可


## 设计目标
1. 要求敏感词文件只被加载一次
设计为单例模式。考虑到存在多线程敏感词过滤的情况，用线程安全下的单例创建去创建单例。

目前暂不考虑多机部署的情况，这个情况其实可以用数据库了，但是谁没事干把这么简单的项目部署到多机上？

如果是需要动态管理敏感词，那么可以这么实现，只需要每次添加敏感词后都要重新把ac自动机的fail指针获取一下就行了。（应该是，待测试，检查是否需要初始化某些参数）

2. 要求具备可扩展性与配置化
根据业务方的不同，需要进行业务扩展，将本项目进行拆分，数据的读取，ac自动机的逻辑实现，文字格式化，敏感词替换都进行隔离，用接口的方式实现，便于扩展。

尤其是对于格式化来说，因为格式化策略有很多，需要基于业务方去配置，是否要求忽略大小写，是否需要忽略简繁体等等，所以用设计模式-策略模式去实现。

3. 性能要求

4. 服务方
是作为sdk处理还是说作为rpc去处理？


## 展望
目前的时间复杂度是O(n)的，但只能实现一些基础功能，无法实现比较难的。

反正都是基于业务方自己去配置，而且以下功能都挺常见的，那么都实现一下吧。

1. 检测网站
2. 检测邮箱
3. 忽略重复词 (目前不会写，感觉时间复杂度会是O(n^2)的)
比如fffuuck这种词汇，应该被过滤掉。
4. 模糊过滤
5. 检测手机号码


## 反和谐
已经实现了敏感词过滤，那么能否实现反敏感词过滤呢？

1. 修改文字变成类似的字
在unicode中，有很多字，它们看起来长得很像，但是它们的ord不一样。只需要把字体变成不同的即可。
参考：https://github.com/RimoChan/unvcode 但是这个项目性能存在问题，尤其是用java实现，大概要7s才能计算出来。
2. 拆字
中文里左右偏旁的文字进行拆字，还是可以被人读懂的。

