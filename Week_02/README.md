GC优化不能解决一切性能问题，它是最后的调优手段。

当代主流虚拟机（Hotspot VM）的垃圾回收都采用“分代回收”的算法。“分代回收”是基于这样一个事实：对象的生命周期不同，所以针对不同生命周期的对象可以采取不同的回收方式，以便提高回收效率。
Hotspot VM将内存划分为不同的物理区，就是“分代”思想的体现。JVM内存主要由新生代、老年代、永久代构成。

不同的垃圾回收器，适用于不同的场景。常用的垃圾回收器：
串行（Serial）回收器是单线程的一个回收器，简单、易实现、效率高。
并行（ParNew）回收器是Serial的多线程版，可以充分的利用CPU资源，减少回收的时间。
吞吐量优先（Parallel Scavenge）回收器，侧重于吞吐量的控制。
并发标记清除（CMS，Concurrent Mark Sweep）回收器是一种以获取最短回收停顿时间为目标的回收器，该回收器是基于“标记-清除”算法实现的。

运行结果
使用java -Xmx2g -Xms2g -XX:+PrintGCDetails GCLogAnalysis 效率是最高的，最大堆内存为物理内存的25%
使用并行GC java -XX:+UseParallelGC -Xmx512m -Xms512m -XX:+PrintGCDetails GCLogAnalysis 发生27次GC，每次9-15ms， 6次Full GC，每次50-70ms
使用串行化gc java -XX:+UseSerialGC -Xmx512m -Xms512m -XX:+PrintGCDetails GCLogAnalysis 发生12次GC,平均每次耗时33ms 将最大堆内存设置为2GB，发生5次GC，每次80ms
使用CMS java -XX:+UseConcMarkSweepGC -Xmx512m -Xms512m -XX:+PrintGCDetails GCLogAnalysis 发生12次GC，每次11-48ms
使用G1GC java -XX:+UseG1GC -Xmx512m -Xms512m -XX:+PrintGC GCLogAnalysis