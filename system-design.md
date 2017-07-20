## CAP Theorem
consistency vs availability vs partition tolerance
network partitioning is a must. so generally consistency vs availability.
RDBMS choose consistency over availability.
nosql choose availability over consistency.

reference:
https://en.wikipedia.org/wiki/CAP_theorem
https://www.youtube.com/watch?v=hUd_9FENShA (*****)

https://en.wikipedia.org/wiki/Quorum_(distributed_computing)
Vr + Vw > V
Vw > V/2


## consensus algo (paxos)

https://www.youtube.com/watch?v=UUQ8xYWR4do


## consistent hashing
http://michaelnielsen.org/blog/consistent-hashing/

## distributed unique primary keys

Flickr의 예..
mysql을 Tickets64로 auto_increment로 사용해서 유닉 ID를 생성해서 주는 서보로 사용함. 
이렇게 하면 SPOF가 되니까 두대의 서버를 둔뒤, 한대는 홀수 , 다른 한대는 짝수를 생성하게 해서 SPOF 안되게 함. 
아주 쉽게 구현한 예..
http://code.flickr.net/2010/02/08/ticket-servers-distributed-unique-primary-keys-on-the-cheap/

twitter snowflack
현재 twitter id는 64bit unsigned int이고 무지 크다..
41bit : timestamp(milli second)
10bit : machine id 
12bit : sequential number
https://blog.twitter.com/engineering/en_us/a/2010/announcing-snowflake.html

## Kafka(publisher/subscriber)


https://www.infoq.com/articles/apache-kafka


## vector clock

http://basho.com/posts/technical/why-vector-clocks-are-easy/


## spatial search 

wonderful explanation for RTree, KdTree
https://blog.mapbox.com/a-dive-into-spatial-search-algorithms-ebd0c5e39d2a

explaining NN search for kdree, https://en.wikipedia.org/wiki/K-d_tree

## CORS
기본적으로 http request는 다른 오리진의 put, post요청을 허용하지 않는다. default security를 확보하기 위해서.
그런데 웹 환경이 점점 복잡해지자 다른 도메인에 요청을 주고 받아야 할 필요가 생겼는데..
허용할 것인지 말것인지에 대해서 기술하는 스펙을 CORS라고 한다.
도메인 A에서 도메인 B로 요청을 할때 도메인 B의 서버는 CORS로 제한/허용할 수 있다.
아래처럼 

```
Access-Control-Allow-Origin: *
Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS
```
https://brunch.co.kr/@adrenalinee31/1




## things to study

[Distributed Systems]



http://www.allthingsdistributed.com/2008/12/eventually_consistent.html
http://michaelnielsen.org/blog/consistent-hashing/ (*****)
https://lethain.com/introduction-to-architecting-systems-for-scale/
http://www.aosabook.org/en/distsys.html
https://www.cl.cam.ac.uk/teaching/0910/ConcDistS/11a-cons-tx.pdf
http://basho.com/posts/technical/why-vector-clocks-are-easy/

[Distributed Key-Value Database]

Dynamo: Amazon’s Highly Available Key-value Store (*****)
Cassandra – A Decentralized Structured Storage System
Bigtable: A Distributed Storage System for Structured Data
https://www.slideshare.net/guestdfd1ec/design-patterns-for-distributed-nonrelational-databases
http://horicky.blogspot.com/2009/11/nosql-patterns.html
[Sharding]

https://medium.com/@Pinterest_Engineering/sharding-pinterest-how-we-scaled-our-mysql-fleet-3f341e96ca6f (*****)
http://highscalability.com/blog/2013/4/15/scaling-pinterest-from-0-to-10s-of-billions-of-page-views-a.html
[Caching]

http://highscalability.com/blog/2013/7/8/the-architecture-twitter-uses-to-deal-with-150m-active-users.html (*****)
https://content.pivotal.io/blog/using-redis-at-pinterest-for-billions-of-relationships
https://medium.com/@Pinterest_Engineering/building-a-follower-model-from-scratch-b51a08c5b54e
http://highscalability.com/blog/2013/4/15/scaling-pinterest-from-0-to-10s-of-billions-of-page-views-a.html
https://www.slideshare.net/oemebamo/introduction-to-memcached
[Search]

https://www.elastic.co/blog/found-elasticsearch-from-the-bottom-up#sthash.wlshw2Cj.dpbs
https://www.elastic.co/blog/found-elasticsearch-top-down#sthash.gZwYNymj.dpbs
https://blogs.dropbox.com/tech/2015/03/firefly-instant-full-text-search-engine/
[Distributed Consensus]

Paxos Made Simple (*****)
Paxos Made Live – An Engineering Perspective (*****)
The Chubby Lock Service for Loosely-Coupled Distributed Systems
ZooKeeper: Wait-free coordiination for Internet-scale systems (*****)
https://www.youtube.com/watch?v=WX4gjowx45E
https://www.quora.com/Distributed-Systems-What-is-a-simple-explanation-of-the-Paxos-algorithm
[Concurrency]

https://caffinc.github.io/2016/03/simple-threadpool/
http://javarevisited.blogspot.com/2012/02/producer-consumer-design-pattern-with.html
http://javarevisited.blogspot.com/2015/07/how-to-use-wait-notify-and-notifyall-in.html
http://www.thinkingparallel.com/2007/07/31/10-ways-to-reduce-lock-contention-in-threaded-programs/
[Distributed Queue]

https://kafka.apache.org/documentation/#quickstart_download (*****): 놀랍게 많은 수의 디자인 인터뷰가 Queue에 관한 것이다. Kafka의 디자인을 살펴보는것이 도움이 많이 된다.
http://docs.aws.amazon.com/AWSSimpleQueueService/latest/SQSDeveloperGuide/sqs-visibility-timeout.html
[그외]

https://www.slideshare.net/davegardnerisme/unique-id-generation-in-distributed-systems (*****)
https://neil.fraser.name/writing/sync/
http://highscalability.com/all-time-favorites/
http://work.tinou.com/2012/09/write-ahead-log.html
https://www.slideshare.net/AmazonWebServices/spot302-under-the-covers-of-aws-core-distributed-systems-primitives-that-power-our-platform-aws-reinvent-2014
https://github.com/checkcheckzz/system-design-interview

