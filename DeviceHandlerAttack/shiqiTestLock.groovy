/**
 *  TestLock
 *
 *  Copyright 2016 Yunhan Jia & Shiqi Wang
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 */
definition(
    name: "TestLock",
    namespace: "jyh0082007",
    author: "Yunhan Jia & Shiqi Wang",
    description: "a",
    category: "Safety & Security",
    iconUrl: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience.png",
    iconX2Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience@2x.png",
    iconX3Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience@2x.png",
    oauth: true)


preferences {
	section("Title") {
    	input "lock", "capability.battery", title:"door lock", required: true, multiple: false
		// TODO: put inputs here
	}
}

def installed() {
	log.debug "Installed with settings: ${settings}"

	initialize()
}

def updated() {
	log.debug "Updated with settings: ${settings}"

	unsubscribe()
	initialize()
}

def initialize() {
	subscribe(lock,'lock.lock',handler)
	// TODO: subscribe to attributes, devices, locations, etc.
}
def handler(evt){
	log.debug "$evt.value"
	if(evt.value == "locked"){
    	  lock.unlock()
    }

  
   
    
}
// TODO: implement event handlers