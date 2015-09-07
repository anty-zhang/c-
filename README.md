# c＋＋
c++ basic


# 指针和引用

----------------------------------------------------------------
## 1.指针
1.1 ①保存地址
	②加*取所指变量
	③运算以相应的类型变量为单位
结构指针：(*指针).成员 或者  指针—>成员

1.2 数组名和指针区别：
数组名本身没有存储空间，不能对数组名进行赋值，数组名就是一个地址；而指针是个变量，本身有自己的存储空间，并且保存指针空间的大小是一样的。例如：
如，int *p= new int[n];
例：int a[10];
	int *p=a;
	*a=a[0]=*p;
	*(a+i) = *(p+i) = a[i]=*p++;

1.3 指针修饰变量
1.3.1 指向常量的指针
是指一个指向常量的指针变量。它不允许改变指针所指的变量，但是可以将这个指针赋予另一个常量。如
		const char * name = “zhang”;//声明指向常量的指针
		name[3]=’a’;//错误，常量不允许修改
		name = “li”;//正确，可以修改指针
本质：const int *p ;禁止修改*p，禁止通过p去修改它所指向的数据，如下程序

1.3.2 常指针
定指针，指针指向一个固定的地址，不能指向别处。如
char * const name = “chang”;//声明为一个名为name的指针变量，该指针是指向字符型数据的常指针，用”chang”的地址来初始化该常指针。
创建一个常指针就是创建一个不能移动的固定指针，但是它所指的数据可变如下
name[3]=’a’;//正确
name = “li”;//错误

1.3.3 指向常量的常指针
是指这个指针本身不能改变，这个指针所指向的常量也不能改变。如

const char * const name=”zhang”;
		name[3]=’a’;//错误
		name=”li”;//错误

1.4 函数指针
函数指针:函数也有地址，函数名表示地址
类型：去掉函数名，剩下的就是类型
除有赋值和调用，没有任何运算，不能输出函数指针的值
像声明函数一样定义函数指针，把函数名改成(*指针)
例如：basic/func_point.cpp


1.5 注意：
1.5.1 不管什么指针都占4个字节内存空间(32位占4个字节，64位占8个字节)。例，basic/size_point.cpp
1.5.2 字符指针，当输出字符指针的地址时，输出的是从这个地方一直遇到\0时停止。例，basic/character_point.cpp


## 2.引用
2.1
常量引用：(1)接受常量的初始化
			(2)禁止修改这个引用
原则：(1)尽量用引用传递参数
	   (2)尽量用const限制对引用的修改
一个变量的类型和地址都是终身不变的

2.2 引用和非引用的区别
	引用：直接使用实参
	非引用：会把实参复制一份给形参
	形参：是一个新变量，调用的时候时创建，有实参来初始化


## 3. 使用指针和引用的注意事项
(1)保证那个变量有”以后”
(2)从函数返回的那个变量还存在。例如：basic/var_exists_point.cpp
(3)引用和指针必须初始化

----------------------------------------------------------------


