# system design
- convex hull
    orientation of 3 ordered points : http://www.geeksforgeeks.org/orientation-3-ordered-points/

```
a x b
    = ax*by-ay*bx
    = |a||b|*sin(theta)
```
이므로, 0~180도면 clockwise, -180~0 도면 counterclockwise가 됨. 따라서 세점의 상대위치를 알 수 있음.
ccw에 대한 설명 : http://jason9319.tistory.com/358



## android

http://pierrchen.blogspot.kr/2014/06/android-ui-internal-design-of.html

- android internal http://newandroidbook.com/
- input flinger, http://newandroidbook.com/Book/Input.html?r
- android ipc, https://www.slideshare.net/jserv/android-ipc-mechanism

### SurfaceFlinger

SurfaceFlinger(server)  <--> Surface(client)

- [여기에 따르면](https://source.android.com/devices/graphics/arch-sv-glsv) source crop과 실제 프레인 영역의 사이즈가 다른데 surface flinger가 스케일링 까지 담당함.
- SurfaceView는 UI surface와 별도의 bufffer를 사용하기 때문에 앱의 UI와는 독릭적이다. 
- surface는 bufferqueue의 producer이고 surfaceflinger는 consumer이다. 


references
- surface flinger, https://www.slideshare.net/jserv/design-and-concepts-of-android-graphics
- at a glance, https://www.imgtec.com/blog/learning-about-the-android-graphics-subsystem/


### Treble

- Android 8.0 (O)부터 Treble이 들어가기 시작함.
- binderized HAL, same-process HAL, passthrough HAL 로 나눔.
- aidl처럼 hidl이 생김


references:
- project treble, https://www.youtube.com/watch?v=Ksegw928gUM&t=1144s


### binder

```
 Client - proxy - kernel binder - stub - server
```

clinet 는 transact()콜 하고, then server receive callback- onTransact(), onTransact() 는 16의의 binder pool 들 중 하나의 쓰레드에서 실행.
즉 동사에 16개의 binder transaction을 처리할 수 있음.

messenger와 halder는 queue를 통해서 처리 되기때문에 느리다. 대신에 thread-safe할 필요는 없다. 반면 aidl은 thread-safe하게 작성해야 한다.
http://codetheory.in/android-interprocess-communication-ipc-with-aidl/


references

- http://codetheory.in/an-overview-of-android-binder-framework/
- binder proxy, binder native http://elecs.tistory.com/173





### class file format

```
struct Class_File_Format {
   u4 magic_number;

   u2 minor_version;
   u2 major_version;

   u2 constant_pool_count;

   cp_info constant_pool[constant_pool_count - 1];

   u2 access_flags;

   u2 this_class;
   u2 super_class;

   u2 interfaces_count;

   u2 interfaces[interfaces_count];

   u2 fields_count;
   field_info fields[fields_count];

   u2 methods_count;
   method_info methods[methods_count];

   u2 attributes_count;
   attribute_info attributes[attributes_count];
}
```


https://www.careercup.com/page?pid=google-interview-questions&topic=system-design-interview-questions
https://github.com/jwasham/google-interview-university/blob/master/README.md#implement-system-routines

http://blog.gainlo.co/index.php/category/system-design-interview-questions/

[ticket servers: distributed unique primary keys on the cheap](http://code.flickr.net/2010/02/08/ticket-servers-distributed-unique-primary-keys-on-the-cheap/)

## youtube viewcounting feature
https://www.careercup.com/question?id=5139174346719232
- 300이하 카운트는 fraud detection없이 그냥 카운트
- db에 바로 쓰면 로드가 많을 것이니, redis나 memcache로 캐쉬 한후 주기적으로 db에 쓰기
- abuser들이 있기 때문에 refresh가 많은 경우나 봇, 이런걸 계산해줘야 함


## architecture for twitter
http://highscalability.com/blog/2013/7/8/the-architecture-twitter-uses-to-deal-with-150m-active-users.html
- dedicate cache for user time line, user home feed,
## numbers for twitter
6000 tweets per second
317M MAU

tips: 100M requests per day -> 1000 requests per second.(remove 5 digit)

expected load, storage needed, required network throughput

### snowflake - unique id generator based on timestamp
timestamp, a worker number, and a sequence number.
https://dev.twitter.com/overview/api/twitter-ids-json-and-snowflake

## facebook chat function
http persistent connection to reduce latency


## problems
### manual map reduce
from https://www.careercup.com/question?id=4681660918398976

Given a large network of computers, each keeping log files of visited urls, find the top ten of the most visited urls.
(i.e. have many large <string (url) -> int (visits)> maps, calculate implicitly <string (url) -> int (sum of visits among all distributed maps), and get the top ten in the combined map)
The result list must be exact, and the maps are too large to transmit over the network (especially sending all of them to a central server or using MapReduce directly, is not allowed)


```
Presuming a protocol exists that can ask three questions to each server:

* the score of a single url
* the top 10
* the top n that satisfy score >= N

We program a two pass solution like so:

We denote the number of servers as S.

[First pass]
(1) Ask every server for its own top ten

(2) merge the results. For all URLs in the merged set calculate correct values by asking
all servers for their scores for each URL. Calculate a set of top ten from our sample.

(3) pick score of the now tenth URL as the threshold that we try to beat
in the second round. We denote the threshold as T.

[Second pass]
(4) Ask every server for all its top N that satisfy score >= T/S

(5) Merge these bigger samples again as in step (2)

(6) We now have the correct top ten with correct scores.
```




* strobogrammatic

A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
Write a function to determine if a number is strobogrammatic. The number is represented as a string.
For example, the numbers "69", "88", and "818" are all strobogrammatic

* largest rectangle in matrix
Find the largest rectangle with all 0s in an matrix with only 0 and 1.
from https://techinterviewsolutions.net/2013/03/30/google-interview-questions-compilations/
* settopbox
Given a set top box:
a, b, c, d, e,
f, g, h, i, j,
k, l, m, n, o
p, q, r, s, t
u, v, w, x, y
zWrite code to give the character sequence given a word, For example, if the word is “CON”, the function will print this:
Right//now we’re at B
Right//now we’re at C
OK//to select C
Down
DOwn
Right
Right
OK//to select O
Left//now at N
OK//to select Nnote: Be careful when you’re at Z. if you go to the right, you will get stuck.
Afterwards, the interviewer adds a space to the right of ‘Z’ to test the code.
from https://techinterviewsolutions.net/2013/03/30/google-interview-questions-compilations/

* query auto-completion
Design distributed backend side for query auto-completion feature

https://techinterviewsolutions.net/2013/03/30/google-interview-questions-compilations/
* design a google gmail system

* sync
http://www.gilgil.net/communities_kr/14456  synchronization 관련 문제 인듯 한데..
