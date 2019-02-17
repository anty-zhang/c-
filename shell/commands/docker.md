# docker（ubuntu 16.04）

```bash

sudo apt-get install apt-transport-https ca-certificates
sudo apt-key adv --keyserver hkp://p80.pool.sks-keyservers.net:80 --recv-keys 58118E89F3A912897C070ADBF76221572C52609D

echo "deb https://apt.dockerproject.org/repo ubuntu-xenial main" | sudo tee -a /etc/apt/sources.list
sudo apt-get update

sudo apt-get install docker-engine
# start
sudo systemctl enable docker
sudo systemctl start docker

# add docker user
sudo groupadd docker

sudo usermod -aG docker $USER

groups $USER

docker --version
docker stop webserver

# 公共镜像
https://hub.docker.com/explore/

```

# 获取镜像

```bash
docker pull --help
docker pull [选项] [Docker Registry地址]<仓库名>:<标签>

Docker Registry地址:地址的格式一般是 <域名/IP>[:端口号] 。默认地址是 Docker Hub。
仓库名:如之前所说，这里的仓库名是两段式名称，既 。 对于 Docker Hub，如果不给出用户名，则默认为library，也就是官方镜像。
```

# 镜像

```bash
docker run -it --rm ubuntu:14.04 bash
docker images

# 悬空镜像
docker images -f dangling=true
# 删除悬空镜像
### docker rmi $(docker images -q -f dangling=true)

# 中间层镜像
docker images -a

# 查看mongo:3.2之后的镜像
docker images -f since=mongo:3.2
# 查看mongo:3.2之前的镜像
docker images -f before=mongo:3.2
# 镜像format输出
docker images --format "{{.ID}}: {{.Repository}}"
docker images --format "table {{.ID}}\t{{.Repository}}\t{{.Tag}}"

## docker commit---将容器的存储层保存下来成为镜像
docker run --rm --name webserver -d -p 80:80 nginx
docker exec -it webserver bash
echo '<h1>Hello, Docker!</h1>' > /usr/share/nginx/html/index.html
exit
curl http://localhost

docker diff webserver
docker commit --author "guoqiang@xxx.com"  -m 'echo hello' webserver nginx:v2
docker history nginx:v2
docker history nginx:latest
docker run --rm --name web2 -d -p 81:80 nginx:v2

```

# 使用dockerFile定制镜像

```bash
# dockerfile---修改，配置，安装，构建，操作命令脚本

```

# Dockerfile命令

```bash
每个指令都会建立一层

# FROM
scratch是虚拟镜像。静态编译类型可以使镜像体积变小

# RUN
shell 格式：RUN <命令> 
exec 格式：RUN <"可执行文件", "参数1", "参数2">

# COPY

# ADD
尽量用COPY

# CMD 只可以出现一次，如果写了多个， 只有最后一个生效。

# ENTRYPOINT 只可以出现一次，如果写了多个， 只有最后一个生效。
# ENV
# ARG
# VOLUME
# EXPOSE
# WORKDIR
# USER
# HEALTHCHECK 只可以出现一次，如果写了多个， 只有最后一个生效
# ONBUILD
# 

docker inspect --format '{{json .State.Health}}' web | python -m json.tool

docker build -t nginx:v3 .
"点" 是指定上下文路径
构建方式：
1）普通方式
2）git方式
3）tar方式
4）从标准输入中读取Dockerfile文件进行构建： docker build - < Docerfile 或者 cat Dockerfile build -
docker build - < xx.tar.gz

```

# docker容器操作

```bash
docker start docker stop docker restart docker attach docker nsenter
```

# 找到容器webserver的进程id

```bash
docker inspect --format "{{ .State.Pid }}" webserver sudo nsenter --target 32090 --mount --uts --ipc --net --pid sudo nsenter --target 32090 --mount --uts --ipc --net --pid -- /usr/bin/env --ignore-environment HOME=/root /bin/bash --login

wget -P ~ wget -P ~ https://github.com/yeasy/docker_practice/raw/master/_local/.bashrc_docker echo "[ -f ~/.bashrc_docker ] && . ~/.bashrc_docker" >> ~/.bashrc; source ~/.bashrc

sudo docker export 7691a814370e > ubuntu.tar cat ubuntu.tar | sudo docker import - test/ubuntu:v1.0 sudo docker import http://example.com/exampleimage.tgz example/imagerepo

docker rm $(docker ps -a -q)

```

# docker数据管理
```bash
# 数据卷

docker run -d -P --rm --name web1 -v /webapp nginx:latest
docker run -d -P --rm --name web2 -v /home/xiaoqiang/tmp:/webapp:ro nginx:latest

# -v 标记主机一个文件挂在到容器中
sudo docker run --rm -it -v ~/.bash_history:/.bash_history ubuntu /bin/bash

# 数据卷容器： 容器中数据共享
sudo docker run -d -v /dbdata --name dbdata training/postgres echo Data-only container for postgres

sudo docker run -d --volumes-from dbdata --name db1 training/postgres
sudo docker run -d --volumes-from dbdata --name db2 training/postgres

# 数据卷容器备份，恢复，迁移
docker run  --volumes-from dbdata  -v $(pwd)/tmp:/backup ubuntu:16.04 tar -cvf /backup/backup.tar /dbdata

docker run -v /dbdata --name dbdata2 ubuntu:16.04 /bin/bash

docker run --volumes-from dbdata2 -v $(pwd)/tmp:/backup busybox tar xvf /backup/backup.tar

```

# docker 使用网络

```bash
docker run -d --name web1 -P training/webapp python app.py
docker logs -f web1
docker run -d --name web1 -p 5000:5000 training/webapp python app.py
docker run -d --name web1 -p 5000:5000 -p 5001:5001 training/webapp python app.py
docker run -d --name web1 -p 127.0.0.1:5000:5000 training/webapp python app.py
docker run -d --name web1 -p 127.0.0.1::5000 training/webapp python app.py
docker run -d --name web1 -p 127.0.0.1::5000/udp training/webapp python app.py

docker port web1 5000

# 容器互联
# link name:alias
docker run -d --name db training/postgres
docker run -d -P --name web --link db:db training/webapp python app.py

# docker 两种方式为容器公开连接信息
# 环境变量
docker run --rm --name web2 --link db:db training/webapp env

# 更新/etc/hosts文件
docker run -it --rm --link db:db training/webapp /bin/bash
cat /etc/hosts
apt-get install -yqq inetutils-ping
ping db


# 其中有些命令选项只有在 Docker 服务启动的时候才能配置，而且不能马上生效。
# -b BRIDGE or --bridge=BRIDGE  --指定容器挂载的网桥 
# --bip=CIDR  --定制 docker0 的掩码
# -H SOCKET... or --host=SOCKET...  --Docker 服务端接收命令的通道 
# --icc=true|false  --是否支持容器之间进行通信
# --ip-forward=true|false  --请看下文容器之间的通信。 Docker 就会自动设定 系统的 ip_forward 参数为 1。
# --iptables=true|false  --是否允许 Docker 添加 iptables 规则
# --mtu=BYTES  --容器网络中的 MTU
#  
# 下面2个命令选项既可以在启动服务时指定，也可以 Docker 容器启动( docker )时候指定。在 Docker 服务启动的时候指定则会成为默认值，后面执行docker run时可以覆盖设置的默认值。
# 不指定的话，docker默认会用宿主机器上的/etc/resolv.conf来配置容器
#  --dns=IP_ADDRESS...  --使用指定的DNS服务器，让容器通过这个服务器来解析所有不再/etc/hosts中的主机名
#  --dns-search=DOMAIN...  --指定DNS搜索域

# 最后这些选项只有在 docker run 执行时使用，因为它是针对容器的特性内容。

# -h HOSTNAME or --hostname=HOSTNAME  --配置容器主机名， 会写到容器的/etc/hosts和/etc/hostname中
# --link=CONTAINER_NAME:ALIAS  --添加到另一个容器的连接
# --net=bridge|none|container:NAME_or_ID|host  --配置容器的桥接模式 
# -p SPEC or --publish=SPEC  --映射容器端口到宿主主机
# -P or --publish-all=true|false  --映射容器所有端口到宿主主机

# 配置dns
在容器中mout可以看到
... on /etc/hostname
... on /etc/hosts
... on /etc/resolv.conf
这种机制可以在宿主主机dns发生变更后，容器中的dns配置通过/etc/resolv.conf文件立即得道更新


# 容器访问外部网络
sysctl net.ipv4.ip_forward

$sysctl -w net.ipv4.ip_forward=1 # 手动开启转发


# 配置 docker0 网桥
sudo apt install bridge-utils
sudo brctl show
ip addr show eth0
ip route

# 自定义网桥
# 停止服务，并删除旧的网桥
sudo service docker stop
sudo ip link set dev docker0 down
sudo brctl delbr docker0

# 然后创建一个网桥 bridge0
sudo brctl addbr bridge0
sudo ip addr add 192.168.5.1/24 dev bridge0
sudo ip link set dev bridge0 up
ip addr show bridge0

# 配置 Docker 服务，默认桥接到创建的网桥上。
echo 'DOCKER_OPTS="-b=bridge0"' >> /etc/default/docker
sudo service docker start

# 网络配置工具
https://github.com/brandon-rhodes/fopnp/tree/m/playground
https://github.com/jpetazzo/pipework

# 创建一个点到点连接
# 默认情况下，Docker 会将所有容器连接到由 docker0 提供的虚拟子网中。 用户有时候需要两个容器之间可以直连通信，而不用通过主机网桥进行桥接。解决办法很简单:创建一对 peer 接口，分别放到两个容器中，配置成点到点链 路类型即可。

```

# dokcer 安全

- 由内核的命名空间和控制组机制，提供容器的内在安全性

- Docker本身的程序抗攻击性

- 内核安全性的加强机制对容器安全性的影响

# Docker Compose项目

- https://github.com/docker/compose

# Docker Machine 项目

- https://github.com/docker/machine

# Docker Swarm项目

- https://github.com/docker/swarm

```bash
docker -H 192.168.0.2:12375 ps
docker -H 192.168.0.2:12375 info

```

# etcd项目
github.com/coreos/etcd