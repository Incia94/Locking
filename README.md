# Locking
Implemeted Basic Algorithms for Locking mechanism

StampedLock - Basic Implementation of Stamped Lock feature in Java 8 which is an optimistic lock and return a
Stamp value to the database when we apply the lock and checks if the lock is stale or not.


Hazel Cast Based Implementation-

locking Code - this is a Pessimistic lock over a thread if a condition is fulfilled and holds the lock
Completely until the mutually exclusive code executes.

It is using a HazelCast Instance which is an Open Source library to handle distributed Locking in a distributed environment with multiple instances of servers.
For sake of simplicity here only one is taken by default.
To add more servers we simply need to create an xml inside the jar file which supports multiple instances.

Optimistic Lock-
This code is trying to show an optimistic lock over a code where instead of lock or unlock we simply use replace.


HazelCast Dependencies 
Jar can be downloaded from - https://hazelcast.com/open-source-projects/downloads/
