# Native Android Motion Tracker Application

A native Android mobile application developed in Java that hooks directly into a device's hardware sensors to track and log physical motion metrics in real time.

## Technical Skills & Architecture
* **Hardware API Integration:** Implemented the native Android `SensorManager` and `SensorEventListener` APIs to stream data from the physical device accelerometer.
* **Real-Time Data Processing:** Processed multi-axis raw sensory inputs (X, Y, and Z vectors) dynamically, rendering updates instantly onto the application interface.
* **Activity Lifecycle Management:** Configured safe memory handling by registering and unregistering hardware listeners during `onResume()` and `onPause()` events to prevent performance degradation and background battery drain.
* **Interface & Debugging:** Developed clean UI bindings using XML layout constraints while tracking operational health through Android logging hooks (`Log.d`).
