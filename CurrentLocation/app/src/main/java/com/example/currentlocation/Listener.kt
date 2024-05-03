package com.example.currentlocation

// Define the listener interface
interface MyEventListener{
    fun onEventTriggered(location: MyLocation)
}

// Class that emits the event
class EventProducer {

    private var listener: MyEventListener? = null

    // Method to register the listener
    fun setEventListener(listener: MyEventListener) {
        this.listener = listener
    }

    // Method to trigger the event
    fun triggerEvent(location: MyLocation) {
        listener?.onEventTriggered(location)
    }
}

// Class that listens for the event
class EventListener(var lambda: (location: MyLocation) -> Unit) : MyEventListener  {
    override fun onEventTriggered(location: MyLocation) {
        lambda(location)
    }
}
