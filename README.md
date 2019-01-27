# 说明

这是浙江大学2018年程序设计方法学的大程MUA解释器。

# 功能

见`report.pdf`

# 测试

要在交互模式下测试MUA，请运行 `java -jar MUA.jar`

若要用MUA解释器运行脚本，例如`test.mua`, 请运行 `java -jar MUA.jar factor.mua`。作者提供了一个`factor.mua`供测试。

注意，在脚本和交互模式下，**每个物理行只能有一条语句**（无返回值的操作），除非存在未匹配的 `[`。例如：

```
print "a print "b
```

是非法的，而

```
make "a [
[]
[print "hi]
]
```

是合法的。
