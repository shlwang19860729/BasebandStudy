package com.example.basebandstudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

private val lessons = listOf(
"通带信号与复基带","采样与离散时间","傅里叶变换与频谱","卷积与 LTI 系统","随机过程基础","功率谱密度与噪声","第一周复习",
"数字调制总览","BPSK 与 QPSK","QAM 与 Gray 编码","最大似然检测 ML","最大后验检测 MAP","匹配滤波器","BER 与性能分析","第二周复习",
"Nyquist 脉冲成形","根升余弦 RRC","定时同步","载波同步与 CFO","信道估计","ZF 与 MMSE 均衡","LMS 自适应算法",
"OFDM 基础","循环前缀 CP","OFDM 导频与估计","信道编码基础","LDPC、Polar 与 LLR","5G NR PHY 总览","端到端接收机","总复习与能力检验"
)
class MainActivity: ComponentActivity() {
 override fun onCreate(savedInstanceState: Bundle?) { super.onCreate(savedInstanceState); setContent { BasebandApp() } }
}
@Composable fun BasebandApp() {
 var done by remember { mutableStateOf(setOf<Int>()) }
 var selected by remember { mutableIntStateOf(-1) }
 MaterialTheme(colorScheme = darkColorScheme(primary= androidx.compose.ui.graphics.Color(0xFFC5B5FF), background=androidx.compose.ui.graphics.Color(0xFF101114), surface=androidx.compose.ui.graphics.Color(0xFF1D1C21))) {
  Surface(modifier=Modifier.fillMaxSize()) {
   if(selected>=0) {
    Column(Modifier.padding(20.dp)) {
     TextButton(onClick={selected=-1}) { Text("← 返回课程") }
     Text("Day ${selected+1}", style=MaterialTheme.typography.labelLarge)
     Text(lessons[selected], style=MaterialTheme.typography.headlineMedium)
     Spacer(Modifier.height(16.dp))
     Text("今日目标：理解${lessons[selected]}的核心概念、关键公式及其在通信接收机中的作用。")
     Spacer(Modifier.height(12.dp)); Text("建议学习 120 分钟：概念30m · 推导45m · 工程理解25m · 复盘20m")
     Spacer(Modifier.height(20.dp))
     Button(onClick={done=if(selected in done) done-selected else done+selected}) { Text(if(selected in done) "取消打卡" else "完成今日打卡") }
    }
   } else LazyColumn(Modifier.padding(16.dp),contentPadding=PaddingValues(bottom=24.dp)) {
    item { Text("BASEBAND · 30 DAY", color=MaterialTheme.colorScheme.primary); Text("Baseband Study",style=MaterialTheme.typography.headlineLarge); Text("每天学习通信基带 · 理论优先 · 每天约2小时"); Spacer(Modifier.height(12.dp)); Text("完成 ${done.size}/30 · ${done.size*100/30}%"); Spacer(Modifier.height(12.dp)) }
    itemsIndexed(lessons) { i, lesson -> Card(Modifier.fillMaxWidth().padding(vertical=5.dp),onClick={selected=i}) { Row(Modifier.padding(14.dp),horizontalArrangement=Arrangement.spacedBy(12.dp)){ Text("${i+1}",color=MaterialTheme.colorScheme.primary); Column(Modifier.weight(1f)){Text(lesson);Text("学习目标与复习",style=MaterialTheme.typography.bodySmall)};Text(if(i in done)"✓" else "›") } } }
   }
  }
 }
}
