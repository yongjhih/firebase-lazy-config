# Firebase Lazy Config

## Usage

Before:

```java
Boolean mForcedUpdateEnabled = null;

public boolean forcedUpdateEnabled() {
  if (mForcedUpdateEnabled == null) {
    mForcedUpdateEnabled = FirebaseRemoteConfig.getInstance().getBoolean("forced_update_enabled");
  }
  return mForcedUpdateEnabled;
}
```

Before:

```kotlin
val forcedUpdateEnabled: Boolean by lazy {
    FirebaseRemoteConfig.getInstance().getBoolean("forced_update_enabled")
}
```

After:

```kotlin
val forcedUpdateEnabled by firebaseBoolean("forced_update_enabled")
```

After:

```java
// TODO
@FirebaseBoolean("forced_update_enabled")
public boolean forcedUpdateEnabled;
```

## Installation

```gradle
repositories {
    jcenter()
    maven { url "https://jitpack.io" }
}

dependencies {
    compile 'com.github.yongjhih:firebase-lazyconfig:0.0.1'
}
```

## License


    Copyright 2017 Andrew Chen

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

