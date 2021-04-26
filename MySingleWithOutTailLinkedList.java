package LinkedList;

import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.LinkedList;


/******************************************************************************
 Singly Linked List with add, remove and get methods.
 Add method also includes sorting first by type of object
 then by estimated checkout date and lastly by name

 @author Croitoru Adrian
 @version Winter 2020
 *******************************************************************************/
public class MySingleWithOutTailLinkedList implements Serializable {

    /**
     * The position of the Top Node
     */
    private Node top;

    /**
     * Size of the Linked List
     */
    public int size;


    /****************************************************************************
     Constructor creates an empty Linked List
     with top pointing to null and size of 0
     ****************************************************************************/
    public MySingleWithOutTailLinkedList() {
        top = null;
        size = 0;
    }

    /****************************************************************************
     Method that returns the size of the Linked list
     @return size of the array
     ****************************************************************************/
    public int size() {
        return size;
    }

    /****************************************************************************
     Empties the Linked List
     with top pointing at null and size being 0
     ****************************************************************************/
    public void clear() {
        top = null;
        size = 0;
    }

    /****************************************************************************
     Add method adds the item to the linked list while also
     @param s is the item of campsite type to be added to the list
     in the appropriate position

     case 1 - top is an RV item while the added item is a Tent
     case 2 - top is a Tent and the added item is also a Tent while there
              are no other items in the list
     case 3 - top is an RV and the added item is also an RV while there are
              no other items in the list
     case 4 - top is a tent and the added item is also a tent
     case 5 - the added item is an RV and the list is empty
     case 6 - list only has RVs and the added item is also an RV
     case 7 - the top item is a Tent and the added item is and RV

     ****************************************************************************/

    public void add(CampSite s) {
        Node tempNode = top;

        //case 0 list is empty and top is null
        if (top == null) {
            size++;
            top = new Node(s, null);
            return;
        }

        //case 1 top is an RV item while the added item is a Tent
        if ((top.getData() instanceof RV) && (s instanceof TentOnly)) {
            size++;
            top = new Node(s, top);
            return;
        }


        //case 2 top is a Tent and the added item is also a Tent while there
        //       are no other items in the list
        //
        //Necessary to avoid nullpointer exception
        if ((top.getData() instanceof TentOnly) && (s instanceof TentOnly) && top.getNext() == null) {
            Node newNode;

            // if the est.date of the top item is smaller/equal than the added item
            // then check the name and put the item in the appropriate position
            if (top.getData().estimatedCheckOut.compareTo(s.estimatedCheckOut) >= 0) {
                if (top.getData().guestName.compareTo(s.guestName) > 0) {
                    newNode = new Node(s, null);
                    size++;
                    top.setNext(newNode);
                    return;
                } else {
                    size++;
                    top = new Node(s, top);
                    return;
                }
                //if the top is bigger, s automatically goes after
            } else {
                newNode = new Node(s, null);
                size++;
                top.setNext(newNode);
                return;
            }
        }

        //Case 3 Top is an RV and the added item is also an RV while there are
        //       no other items in the list
        //Necessary to avoid nullpointer exception
        if ((top.getData() instanceof RV) && (s instanceof RV) && top.getNext() == null) {
            Node newNode;

            // if the est.date of the top item is smaller/equal than the added item
            // then check the name and put the item in the appropriate position
            if (top.getData().estimatedCheckOut.compareTo(s.estimatedCheckOut) >= 0) {
                if (top.getData().guestName.compareTo(s.guestName) > 0) {
                    newNode = new Node(s, null);
                    size++;
                    top.setNext(newNode);
                    return;
                } else {
                    size++;
                    top = new Node(s, top);
                    return;
                }
                //if the top is bigger, s automatically goes after
            } else {
                newNode = new Node(s, null);
                size++;
                top.setNext(newNode);
                return;
            }
        }

        //case 4 top is a tent and the added item is also a tent
        if ((top.getData() instanceof TentOnly) && (s instanceof TentOnly)) {

            int counter = 0;
            Node newNode;

            //goes through the list while avoiding nullpointer exception and infinite loops
            while (tempNode.getNext() != null && (counter < size)) {
                //if the next node is still a tent check the est date and names
                if ((tempNode.getNext().getData() instanceof TentOnly)) {
                    if (tempNode.getData().estimatedCheckOut.compareTo(s.estimatedCheckOut) >= 0) {
                        if (tempNode.getData().guestName.compareTo(s.guestName) > 0) {
                            //if date and name are smaller than added item, and the item as the top,
                            //add the item above the first item
                            if (tempNode == top) {
                                size++;
                                top = new Node(s, top);
                                return;
                            }
                        }
                    }
                }
                //if the next item is tent and the est. date are the same check whose name comes first
                if (tempNode.getNext().getData() instanceof TentOnly
                        && tempNode.getNext().getData().estimatedCheckOut.compareTo(s.estimatedCheckOut) == 0)
                    //if the name does not come first alphabetically, then go down the list once
                    //otherwise place the item above the one in the list.
                    if (tempNode.getNext().getData().guestName.compareTo(s.guestName) > 0) {
                        newNode = new Node(s, tempNode.getNext());
                        tempNode.setNext(newNode);
                        size++;
                        return;
                    } else
                        tempNode = tempNode.getNext();

                //next node is not null check if next node is a Tent and check if the date of the added item
                //is bigger than that of the item in the list. If so go down the list once
                //otherwise increase counter
                if (tempNode.getNext() != null)
                    if (tempNode.getNext().getData() instanceof TentOnly
                            && (s.estimatedCheckOut.compareTo(tempNode.getNext().getData().estimatedCheckOut) > 0)
                            && counter == 0) {
                        tempNode = tempNode.getNext();

                    } else counter++;
            }
            if (tempNode.getNext() != null) {
                newNode = new Node(s, tempNode.getNext());
            } else
                newNode = new Node(s, null);
            size++;
            tempNode.setNext(newNode);
            return;
        }


        // case 5 if the added item is an RV and the list is empty
        // add the item to the list
        if ((s instanceof RV) && top.getNext() == null) {

            size++;
            top = new Node(s, top);
            return;
        }

        // case 6 if the list only has RVs and the added item is also an RV
        if ((top.getData() instanceof RV) && (s instanceof RV)) {

            int counter = 0;
            Node newNode;

            //go through list checking items while avoiding going over the limit of the list
            while (tempNode.getNext() != null && (counter < size)) {
                //if the checked item's date in the list is bigger than the added one check if
                //it's the item is at the top and add it above the top
                if (tempNode.getData().estimatedCheckOut.compareTo(s.estimatedCheckOut) >= 0) {
                    if (tempNode.getData().guestName.compareTo(s.guestName) > 0) {
                        if (tempNode == top) {
                            size++;
                            top = new Node(s, top);
                            return;
                        }
                    }
                }

                //if the added item and checked item have the same date, check if whose name is first/
                //if the added item's name is not first, go down the list
                if (tempNode.getNext().getData().estimatedCheckOut.compareTo(s.estimatedCheckOut) == 0)
                    if (tempNode.getNext().getData().guestName.compareTo(s.guestName) > 0) {
                        newNode = new Node(s, tempNode.getNext());
                        tempNode.setNext(newNode);
                        size++;
                        return;
                    } else
                        tempNode = tempNode.getNext();

                //if after going down the list the next item is not null check if the date is of the added item
                //is bigger than the checked item, keep going down the list
                if (tempNode.getNext() != null)
                    if ((s.estimatedCheckOut.compareTo(tempNode.getNext().getData().estimatedCheckOut) > 0)
                            && counter == 0) {
                        tempNode = tempNode.getNext();

                    } else counter++;
            }

            //if after going through the list we found the correct spot
            //check if that spot is at the end of list
            if (tempNode.getNext() != null) {
                newNode = new Node(s, tempNode.getNext());
            } else
                newNode = new Node(s, null);
            size++;
            tempNode.setNext(newNode);
            return;

        }

        //case 7 if the top item is a Tent and the added item is and RV
        if ((top.getData() instanceof TentOnly) && (s instanceof RV)) {
            int counter = 0;
            Node newNode;
            tempNode = top;

            //go through the list until we reach the item right before an RV or we reach the bottom
            while (tempNode.getNext() != null) {
                if (!(tempNode.getNext().getData() instanceof RV)) {
                    tempNode = tempNode.getNext();
                    counter++;
                } else break;
            }

            //if we reached the bottom and there are no RVs, add the RV item to the bottom and return
            if (tempNode.getNext() == null) {
                newNode = new Node(s, null);
                tempNode.setNext(newNode);
                size++;
                return;
            }

            //Go to the next item which is an RV, while maintaining a pointer to the previous item
            Node prev = tempNode;
            tempNode = tempNode.getNext();

            //if after going to the next item, the one after is null check the added item and the list item
            //and put the added item in the appropriate position
            if (tempNode.getNext() == null) {
                if (tempNode.getData().estimatedCheckOut.compareTo(s.estimatedCheckOut) == 0)
                    if (tempNode.getData().guestName.compareTo(s.guestName) <= 0) {
                        newNode = new Node(s, null);
                        tempNode.setNext(newNode);
                        size++;
                        return;
                    } else {
                        newNode = new Node(s, tempNode);
                        prev.setNext(newNode);
                        size++;
                        return;
                    }
            }

            //if the added and list item are the same date, check the the names,
            //if the added item's name comes first, add the item above the list item
            if (tempNode.getData().estimatedCheckOut.compareTo(s.estimatedCheckOut) == 0)
                if (tempNode.getData().guestName.compareTo(s.guestName) >= 0) {
                    newNode = new Node(s, tempNode);
                    prev.setNext(newNode);
                    size++;
                    return;
                }

            //if the added item's date is lower than the list item, add the item above the list item
            if (tempNode.getData().estimatedCheckOut.compareTo(s.estimatedCheckOut) > 0) {
                newNode = new Node(s, tempNode);
                prev.setNext(newNode);
                size++;
                return;

            }

            //go through the rest of the list of RVs while avoiding null pointer
            while (tempNode.getNext() != null) {
                //if the next item is the same date as the added item, check which name comes first
                //if the added item's name comes first, added it above the list item
                if (tempNode.getNext().getData().estimatedCheckOut.compareTo(s.estimatedCheckOut) == 0)
                    if (tempNode.getNext().getData().guestName.compareTo(s.guestName) > 0) {
                        newNode = new Node(s, tempNode.getNext());
                        tempNode.setNext(newNode);
                        size++;
                        return;
                    }

                //if the added item's date is lower than the list item, added it above the list item
                if (tempNode.getNext().getData().estimatedCheckOut.compareTo(s.estimatedCheckOut) > 0) {
                    newNode = new Node(s, tempNode.getNext());
                    tempNode.setNext(newNode);
                    size++;
                    return;
                }
                //if we couldn't find an appropriate position, go down the list
                tempNode = tempNode.getNext();
            }

            //if we found the appropriate position, check if its last in the list and place it appropriately
            if (tempNode.getNext() != null) {
                newNode = new Node(s, tempNode.getNext());
            } else
                newNode = new Node(s, null);

            size++;
            tempNode.setNext(newNode);
            return;

        }

    }

    /****************************************************************************
     Remove an item at the requested position
     @param index is the item of campsite item position to be removed
     @return returns the deleted campsite item
     ****************************************************************************/
    public CampSite remove(int index) {
        CampSite camps;

        //case 0 list is empty, nothing to remove
        if (top == null)
            return null;
        //case 1 Index out of bounds can't remove
        if (index < 0 || index > size)
            return null;

        //case 2 Index is acceptable and is the first item

        Node tempnode = top;
        if (index == 0) {
            top = top.getNext();
            return tempnode.getData();
        }

        //case 3 Index is acceptable is an item between 1 and size -1
        for (int i = 0; i < size - 1; i++)
            tempnode = tempnode.getNext();
        camps = tempnode.getNext().getData();

        //case 4 Index is the last item on the list
        if (index == size)
            tempnode.setNext(null);
        else tempnode.setNext(tempnode.getNext().getNext());

        //return the deleted item
        return camps;

    }

    /****************************************************************************
     Return an item from the requested position
     @param index is the item of campsite item position to be returned
     @return returns the requested item in position index
     ****************************************************************************/
    public CampSite get(int index) {

        //case 0 if the item requested is out of bounds
        if (index > size || index < 0)
            return null;

        Node temp = top;

        //case 1 if item is not out of bounds, go through the list until we reach the item and return it
        for (int i = 0; i < index; i++) {
            temp = temp.getNext();
        }
        return temp.getData();

    }

    /****************************************************************************
     Returns all the items in the linked list
     ****************************************************************************/
    public void display() {
        Node temp = top;
        while (temp != null) {
            System.out.println(temp.getData());
            temp = temp.getNext();
        }
    }

    /****************************************************************************
     Overriden to String method
     ****************************************************************************/
    @Override
    public String toString() {
        return "MySingleWithOutTailLinkedList{" +
                "top=" + top +
                ", size=" + size +
                '}';
    }
}