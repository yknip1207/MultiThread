package CriticalSectionQueueImproved;

public class CircularQueue {
    private Object[] data;
    private int size;  //目前有多少Obj in data
    private int head;
    private int tail;
    private int maxLen;
    public CircularQueue(int maxLen) {
        data = new Object[maxLen];
        this.maxLen = maxLen;
    }
    
    //除了synchronized(ref)的語法可以鎖定ref指到的物件外,synchronized也可以用在object method前面,表示要鎖定this物件才能執行該方法。
    
    public synchronized Object deQueue() /*throws Exception*/{
    
/***
 * Queue已經沒有資料了,卻還想拿出來
 * 但單純以Exception來處理"Getter在Queue空掉卻還要硬拿"的情況並不合適,因為使用Queue的人必須處理例外狀況,並不斷的消耗CPU資源    
 */
    	//寫法一：不好，不能只單純丟Exception
    	//if(size == 0) throw new Exception();
    	
    	//寫法二：沒東西可以拿的時候就進入wait()
    	// When executing here, Thread must have got lock and be in running mode
        // Let current Thread wait for this object(to sleeping mode)
    	while(size == 0){
    		try{
    			//從Running mode轉到sleep mode，釋出lock
    			wait();
    		} catch(Exception ex){}
    	}
    	
    	//抽Queue的head Obj出來
        Object tmp = data[head];
        data[head] = null;
        head = (head+1)%data.length;
        
        //arra被塞滿了，wake up all Threads waiting for this object
        if(size == data.length)
            notifyAll();
        
        size--;
        return tmp;
    }//release lock
    
/***
 * Queue已經滿了,卻還想塞進去
 * 但單純以Exception來處理"Setter在Queue滿了卻還要硬塞"的情況並不合適,因為使用Queue的人必須處理例外狀況,並不斷的消耗CPU資源    
 */   
    public synchronized void enQueue(Object c) /*throws Exception*/{
    	//寫法一：不好，Queue裡已經塞滿了資料,使用者卻還要放進去
    	//if(size > maxLen) throw new Exception();
    	
    	//寫法二：滿了就wait()
    	// When executing here, Thread must have got lock and be in running mode
        // Let current thread wait for this object(to sleeping mode)
    	while(size == data.length){
    		try{
    			wait();
    		}catch(Exception e){}
    	}
    	
   
        data[tail++] = c;
        tail %= data.length;
        size++;
        
        // wake up all Threads waiting for this object
        // 讓等待該Object的所有Thread進入Runnable Mode。
        if (size==1) 
            notifyAll();
        
    }
    
    
   
   
}
