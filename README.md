# backend-practice
연습


### Jenkins 서버 명령어
```shell script
$ sudo yum install wget
$ sudo yum install maven
$ sudo yum install git
$ sudo yum install docker
$ sudo wget -O /etc/yum.repos.d/jenkins.repo https://pkg.jenkins.io/redhat-stable/jenkins.repo
$ sudo rpm --import https://pkg.jenkins.io/redhat-stable/jenkins.io.key
$ sudo yum install jenkins
$ sudo systemctl start jenkins
$ sudo systemctl status jenkins
```
### instance 서버 명령어
```shell script
$ vi authorized_keys
$ chmod 700 ~/.ssh
$ chmod 600 ~/.ssh/authorized_keys
$ sudo chmod 666 /var/run/docker.sock
```
### 방화벽 규칙 만들기

### 암호화
비대칭키
+ 암호화할때 쓰는 키와 복호화할때 키가 다른 경우
	+ 공개키로 암호화해서 개인키로 복호화해서 사용
	+ 개인키로 암호화한건 개인키로 복호화해서 사용
		+ 개인키로 암호화 한거는 공개키로 복호화 할 수 있다 (서명의 개념)


### nginx로 로드밸런싱 하기
# Nginx 인스턴스에서 다음 명령어로 세팅
sudo yum install nginx
sudo systemctl start nginx

# 로드밸런싱 설정
sudo vi /etc/nginx/nginx.conf
# 위 명령어를 입력하여 nginx 설정 파일로 진입 후 다음과 같이 내용을 변경

upstream cpu-bound-app {
  server {instance_1번의_ip}:8080 weight=100 max_fails=3 fail_timeout=3s;
  server {instance_2번의_ip}:8080 weight=100 max_fails=3 fail_timeout=3s;
  server {instance_3번의_ip}:8080 weight=100 max_fails=3 fail_timeout=3s;
}

location / {
  proxy_pass http://cpu-bound-app;
  proxy_http_version 1.1;
  proxy_set_header Upgrade $http_upgrade;
  proxy_set_header Connection 'upgrade';
  proxy_set_header Host $host;
  proxy_cache_bypass $http_upgrade;
}

# 위 설정이 완료되었으면 Nginx를 reload 시켜야함.
sudo systemctl reload nginx

# Nginx의 에러로그를 확인하기 위한 명령어
sudo tail -f /var/log/nginx/error.log

# 에러를 해결할 수 있는 명령어
sudo setsebool -P httpd_can_network_connect on


# elasticsearch 설정한 것

```shell script
docker network create somenetwork
docker run -d --name elasticsearch --net somenetwork -p 9200:9200 -p 9300:9300 \
-e "discovery.seed_hosts=10.146.0.10,10.146.0.11,10.146.0.13" \
-e "node.name=es01" \
-e "cluster.initial_master_nodes=es01,es02,es03,es04" \
-e "network.publish_host=10.146.0.14" \
elasticsearch:7.10.1
```

```shell script
docker run -d --name elasticsearch -p 9200:9200 -p 9300:9300 \
-e "discovery.seed_hosts=10.146.0.14,10.146.0.11,10.146.0.13" \
-e "node.name=es02" \
-e "cluster.initial_master_nodes=es01,es02,es03,es04" \
-e "network.publish_host=10.146.0.10" \
elasticsearch:7.10.1
```

```shell script
docker run -d --name elasticsearch -p 9200:9200 -p 9300:9300 \
-e "discovery.seed_hosts=10.146.0.14,10.146.0.10,10.146.0.13" \
-e "node.name=es03" \
-e "cluster.initial_master_nodes=es01,es02,es03,es04" \
-e "network.publish_host=10.146.0.11" \
elasticsearch:7.10.1
```

```shell script
docker run -d --name elasticsearch -p 9200:9200 -p 9300:9300 \
-e "discovery.seed_hosts=10.146.0.14,10.146.0.10,10.146.0.11" \
-e "node.name=es04" \
-e "cluster.initial_master_nodes=es01,es02,es03,es04" \
-e "network.publish_host=10.146.0.13" \
elasticsearch:7.10.1
```






