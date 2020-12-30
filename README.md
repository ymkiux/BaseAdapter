# 一个通用的recycleview adapter库

打造一个适用的adapter库

![Release](https://jitpack.io/v/ymkiux/BaseAdapter.svg) ![Apache Licence](http://img.shields.io/badge/license-Apache2.0-ff6600.svg)

#### 引入

~~~
implementation 'com.github.ymkiux:BaseAdapter:v0.0.1-alpha'
~~~

#### 接入模板

> File>Settings>File and Code Templates 点击+号创建  Name 填易记的   Extension填kt

```
#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end
import com.github.adapter.universal.BaseAdapter
import com.github.adapter.universal.BaseViewHolder
class ${NAME}() : BaseAdapter<${LIST_MODEL}?>(R.layout.${item_layout}) {
   
    override fun onBindViewHolder(p0: BaseViewHolder, p1: Int) {
        val ${LIST_MODEL}:${LIST_MODEL}=getListData()[p1]
    }
}
```

使用时右键New查看到刚填写的Name点击填写即可

| File name  |       适配器类名        |
| :--------: | :---------------------: |
| LIST MODEL | Javabean类型 例如String |
| item layout | 适配器布局名称 |

#### 尾言

> 如果觉得还不错,期待你的star！

