/*
 * Copyright 2011 Benjamin Glatzel <benjamin.glatzel@me.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.terasology.model.inventory;

/**
 * @author Benjamin 'begla' Glatzel <benjamin.glatzel@me.com>
 */
public class Inventory {

	private Cubbyhole[] _cubbies;

    public Inventory() {
    	_cubbies = new Cubbyhole[27];
    	
    	for (int i = 0; i < _cubbies.length; i++) {
    		_cubbies[i] = new Cubbyhole();
    	}
    }
    
    public void addItem(Item item, int count) {
    	Cubbyhole free = getFreeCubby(item);
    	Cubbyhole overflow = free.insert(item, count);
    	
    	if (overflow != null) {
    		free = getFreeCubby(item);
    		free.insert(item, overflow.getItemCount());
    	}
    }
    
    public Item removeItemAt(int index, int count) {
    	Cubbyhole cubby = _cubbies[index];
    	
    	cubby.remove(count);
    	
    	return cubby.getItem();
    }
    
    private Cubbyhole getFreeCubby(Item item) {
    	for (Cubbyhole cubby : _cubbies) {
    		if (cubby.getItem() == null) {
    			return cubby;
    		}
    		if (cubby.getItem().equals(item) && !cubby.isFull()) {
    			return cubby;
    		}
    	}
    	
    	return null;
    }
    
    public int getItemCountAt(int cubbyIndex) {
    	return _cubbies[cubbyIndex].getItemCount();
    }
    
    public Item getItemAt(int cubbyIndex) {
    	return _cubbies[cubbyIndex].getItem();
    }
}
