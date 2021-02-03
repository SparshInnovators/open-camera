# open-camera

import dependency

**Step 1** : Add it in your root build.gradle at the end of repositories:
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
  
 **Step 2** : Add the dependency
  ```
  dependencies {
	        implementation 'com.github.SparshInnovators:open-camera:1.2'
	}
```


And then in your Activity you can get the result in onActivityResult(), check for the status code **2001**.

**Java**
```
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == 2001) {
    	  Bitmap image = (Bitmap) data.getExtras().get("data");
         //Your have got the result here
    }
}
```
**Kotlin**

```
 protected fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (requestCode == 2001) {
            val image = data.extras!!["data"] as Bitmap?
            //Your have got the result here
        }
    }
```
    
