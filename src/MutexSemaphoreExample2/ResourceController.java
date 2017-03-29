package MutexSemaphoreExample2;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/***
 * 網址：http://note-whu.rhcloud.com/2015/11/24/semaphore%E7%9A%84%E7%94%A8%E6%B3%95%E8%88%87%E7%89%B9%E8%89%B2/
 * 一個Semaphore uploadSemaphore:最多一次一個thread可以perform上傳
 * 兩個Semaphore downloadSemaphore:最多一次兩個thread可以perform下載
 * 
 *
 */
public class ResourceController {
	//設定一個uploader，兩個downloader
	private final static Semaphore uploadSemaphore = new Semaphore(1);      //當permits為1時，此semaphore為binary semaphore,因為只會有兩種狀態

	private final static Semaphore downloadSemaphore = new Semaphore(2, true);  //後面的布林值為isFair的參數值，若為true,則先請求的就會先拿到，即FIFO的概念。

	/***
	 * 用acquire()去要permit，然後就release()
	 */
	public static void uploadFile(final File file) {
		try {
			uploadSemaphore.acquire();
			System.out.println("upload file for " + Thread.currentThread().getName() + " to server...");
			Thread.sleep(1500);
			System.out.println("upload file successfully.");
			uploadSemaphore.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/***
	 * 用acquire()去要permit，然後就release()
	 */
	public static void downloadFile(final String fileName) {
		try {
			System.out.println("availablePermits: " + downloadSemaphore.availablePermits());    //可利用此方法得知目前還剩幾個permits
			downloadSemaphore.acquire();
			System.out.println("download file for " + Thread.currentThread().getName());
			Thread.sleep(500);
			System.out.println("download file completed.");
			downloadSemaphore.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		

		final ExecutorService executorService = Executors.newCachedThreadPool();
		//上傳service: FileService.isUpload = true
		//下載service: FileService.isUpload = false
		final FileService uploadFileService1 = new FileService(true);
		final FileService uploadFileService2 = new FileService(true);
		final FileService uploadFileService3 = new FileService(true);

		
		//ExecutorService這個Object有個execute這個method，把Runnable的Object丟進去執行
		executorService.execute(uploadFileService1);
		executorService.execute(uploadFileService2);
		executorService.execute(uploadFileService3);

		Thread.sleep(5000);
		System.out.println("---------------------------------------------------------------");

		final FileService downloadFileService1 = new FileService(false);
		final FileService downloadFileService2 = new FileService(false);
		final FileService downloadFileService3 = new FileService(false);
		final FileService downloadFileService4 = new FileService(false);

		executorService.execute(downloadFileService1);
		executorService.execute(downloadFileService2);
		executorService.execute(downloadFileService3);
		executorService.execute(downloadFileService4);

		executorService.shutdown();
	}
}

class FileService implements Runnable {

	private final boolean isUpload;

	public FileService(final boolean isUpload) {
		this.isUpload = isUpload;
	}

	@Override
	public void run() {

		if (this.isUpload) {
			File file = new File("file_path");
			//該thread在run的時候
			ResourceController.uploadFile(file);
		} else {
			ResourceController.downloadFile("file_name");
		}
	}
}