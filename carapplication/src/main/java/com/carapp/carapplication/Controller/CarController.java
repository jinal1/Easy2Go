package com.carapp.carapplication.Controller;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;

import com.carapp.carapplication.DTO.BookingDto;
import com.carapp.carapplication.DTO.CarDto;
import com.carapp.carapplication.Repository.CarBookingRepository;
import com.carapp.carapplication.Repository.CarRepository;
import com.carapp.carapplication.Repository.CustomerCarBookingRepository;
import com.carapp.carapplication.Repository.DriverRepository;
import com.carapp.carapplication.Repository.UserRepository;
import com.carapp.carapplication.Repository.carfeatureRepository;
import com.carapp.carapplication.Repository.CarImageRepository;
import com.carapp.carapplication.Repository.ownerslicenserepository;
import com.carapp.carapplication.model.ownerslicensedeatails;
import com.carapp.carapplication.service.CarService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.carapp.carapplication.model.car1;
import com.carapp.carapplication.model.carbooking;
import com.carapp.carapplication.model.CarImages;
import com.carapp.carapplication.model.CustomerCarBooking;
import com.carapp.carapplication.model.User;
import com.carapp.carapplication.model.carfeature;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.URLConnection;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.sql.Time;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;

import net.sf.jasperreports.repo.InputStreamResource;
@Controller
public class CarController {
	@Autowired
	CarService carservice;
	carfeature feaid;
	car1 car;
	@Autowired
	HttpServletResponse hSR;
	@Autowired
	private Environment env;
    @Autowired
	CarRepository c;
	@Autowired
	CustomerCarBookingRepository cCBR;
	@Autowired
	UserRepository uR;
	@Autowired
	ownerslicenserepository a;
    @Autowired
	CarBookingRepository cbr;
	@Autowired
	CarImageRepository iR;
	@Autowired
	carfeatureRepository cFR;
	@Autowired
	DriverRepository dR;
List<car1> cars;
	/*
	 * @RequestMapping(value = "/") public String getIndexPage1() { return "index";
	 * //return "customer_pickupdropoff"; // return "carimageupload"; // return
	 * "home"; //return "maincarpage";
	 * 
	 * }
	 */

	/*@RequestMapping(value = "/getafterlogin")
	public String getafterlogin()
	{
		return "adpanel";
	}
	@RequestMapping(value = "/getafterloginforcustomer")
	public String getafterlogincustomer()
	{
		return "customer";
	}*/
	
	
	@RequestMapping(value ="/setcustomerbooking/{id}", method = RequestMethod.POST)
	
	public  ResponseEntity<Map<String,String>> setcustomerbooking(@RequestBody CustomerCarBooking customercarbooking,@PathVariable("id") int id) 
	{
		
		Map<String,String> map = new  HashMap<>();
		Map<Integer,Integer> map1 = new  HashMap<>();
		int car1id=id;
		System.out.println("in setcustomerbooking method ");
		List<java.sql.Date> a=carservice.getcarbookingdate(id);
		java.sql.Date p,q;
	p=a.get(0);
	q=a.get(1);
	System.out.println("in setcustomerbooking method "+p+q);
	System.out.println("hello");
	 User user=(User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println("hello"+user.getUserid());
	java.sql.Date cucarpick=customercarbooking.getPickupDate();
	System.out.println("hello"+cucarpick+user.getUserid());
	java.sql.Date cucardrop=customercarbooking.getDropoffDate();
	java.util.Date p1=new java.util.Date(p.getTime());
	java.util.Date q1=new java.util.Date(q.getTime());
	java.util.Date cucarpick1=new java.util.Date(cucarpick.getTime());
	java.util.Date cucardrop1=new java.util.Date(cucardrop.getTime());
	System.out.println("hello"+cucarpick+cucardrop+user.getUserid());
	System.out.println(""+p1+q1+cucarpick1+cucardrop1);
	if(p.compareTo(q)<0 && cucarpick.compareTo(cucardrop)<0)  
	{
if(p.compareTo(cucarpick)<=0 && q.compareTo(cucardrop)>=0)
    {
		
		System.out.println("in setcustomerbooking method jinal"+cucarpick+cucardrop);
		
		customercarbooking.setCustomerid(user.getUserid());
		System.out.println(""+user.getUserid());
		System.out.println(customercarbooking.getDropoffTime());
		cCBR.save(customercarbooking);
		System.out.println(customercarbooking.getDropoffTime());
		car1 car1=c.findOne(id);
		car1.setCustcarbook(customercarbooking);
		System.out.println(customercarbooking.getDropoffTime());
		c.save(car1);
		
	}
	}
	
	String dr=customercarbooking.getDriverRequired();
	
	
	
	System.out.println(dr);
	String car2id=""+car1id;
	System.out.println(dr);
	String sdate=""+customercarbooking.getPickupDate();
	System.out.println(dr);
	String edate=""+customercarbooking.getDropoffDate();
	System.out.println(dr);
	map.put("id",car2id);
	System.out.println(dr);
	map.put("driverre",dr);
	System.out.println(dr);
	//only for calculating totalprice

System.out.println(dr);

car1 car1=c.findOne(id);
int price=car1.getPrice();
	System.out.println("after 7");
	int year=customercarbooking.getPickupDate().getYear()+1900;
	System.out.println("8");
	int year1=customercarbooking.getDropoffDate().getYear()+1900;
	System.out.println("9"+year+year1);
	map1.put(1,31);
	map1.put(2, 28);
	map1.put(3,31);
	map1.put(4, 30);
	map1.put(5, 31);
	map1.put(6, 30);
	map1.put(7, 31);
	map1.put(8, 31);
	map1.put(9,30);
	map1.put(10, 31);
	map1.put(11,30);
	map1.put(12, 31);
	System.out.println("10");
	int totaldays=0;
	int mon[]= new int[12];
	int yeararray[]= new int[15];
	int i=0;
	int yearl=year+1;
	//for different year
	if(year!=year1)
	{
		int ydif=year1-year;
		int month=customercarbooking.getPickupDate().getMonth()+1;
		int month1=customercarbooking.getDropoffDate().getMonth()+1;
		int monthl=month;
		int monthl1=month1;
		if(ydif==1)
		{
			int daysinstartdate=map1.get(month);
			int daysinenddate=map1.get(month1);
			int date=customercarbooking.getPickupDate().getDate();
			
			int date1=customercarbooking.getDropoffDate().getDate();
			System.out.println(daysinstartdate);
			System.out.println(daysinenddate);
			System.out.println(date);
			System.out.println(date1);
			totaldays=totaldays+(daysinstartdate-date+1);
			System.out.println(totaldays);
			while(monthl<=12)
			{
				
			totaldays=totaldays+(map1.get(monthl));
			monthl=monthl+1;
			System.out.println(totaldays);
			}
			totaldays=totaldays+date1;
			System.out.println(totaldays);
		for(int j=1;j<month1;j++)
		{
			totaldays=totaldays+map1.get(j);
			System.out.println(totaldays);
		}
		}
		else
		{
			System.out.println(""+ydif);
			while(yearl<year1)
			{
				
			yeararray[i]=yearl;
			i++;
			yearl=yearl+1;
			
			
			if(yeararray[i]%4==0)
			{
				totaldays=totaldays+366;
			}
			else
			{
				totaldays=totaldays+365;
			}
			System.out.println(totaldays);
			}
			int daysinstartdate=map1.get(month);
			int daysinenddate=map1.get(month1);
			int date=customercarbooking.getPickupDate().getDate();
			
			int date1=customercarbooking.getDropoffDate().getDate();
			System.out.println(daysinstartdate);
			System.out.println(daysinenddate);
			System.out.println(date);
			System.out.println(date1);
			totaldays=totaldays+(daysinstartdate-date+1);
			System.out.println(totaldays);
			while(monthl<=12)
			{
				
			totaldays=totaldays+(map1.get(monthl));
			monthl=monthl+1;
			System.out.println(totaldays);
			}
			totaldays=totaldays+date1;
			System.out.println(totaldays);
		for(int j=1;j<month1;j++)
		{
			totaldays=totaldays+map1.get(j);
			System.out.println(totaldays);
		}
		System.out.println("in year"+totaldays);
		}
		
		
	}
	
	//for same year
	if(year==year1)
	{
		totaldays=0;
		i=0;
		int month=customercarbooking.getPickupDate().getMonth()+1;
		int month1=customercarbooking.getDropoffDate().getMonth()+1;
		int monthl=month+1;
		if(month<=month1)
		{
			if(year%4==0)
			{
				map1.put(2,29);
			}
			int daysinstartdate=map1.get(month);
		int daysinenddate=map1.get(month1);
		
		int date=customercarbooking.getPickupDate().getDate();
		int date1=customercarbooking.getDropoffDate().getDate();
			int dif=month1-month;
			System.out.println(dif);
			System.out.println(month);
			System.out.println(month1);
			System.out.println(monthl);
			if(dif==1)
			{
				
				System.out.println(totaldays);
				System.out.println(daysinstartdate);
				System.out.println(date);
				System.out.println(daysinenddate);
				totaldays=totaldays+(daysinstartdate-date+1)+date1;
				
				System.out.println("in month"+totaldays);
			}
			else
			{
			while(monthl<month1)
			{
			mon[i]=monthl;
			System.out.println(mon[i]);
			System.out.print(monthl);
			totaldays=totaldays+map1.get(mon[i]);
			
			System.out.println(totaldays);
			monthl=monthl+1;
			i=i+1;
			}
		
			
			
			if(date<=daysinstartdate && date1<=daysinenddate)
			{
				totaldays=totaldays+(daysinstartdate-date+1)+date1;
				System.out.println(totaldays);
				System.out.println(daysinstartdate);
				System.out.println(date1);
			}
			System.out.println("different month"+totaldays);
			}
			
		}
			
	}
	
	price=price*totaldays;
	String price2=""+price;
	

	map.put("startdate",sdate);
	map.put("enddate", edate);
	
	map.put("price", price2);

	return new ResponseEntity<Map<String,String>>(map,HttpStatus.OK);
	}
	
	public void givereturncardetails(List<car1> cars)
	{
		this.cars=cars;
		
	}
	
	@RequestMapping(value = "/getreturncarrequest", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, List<car1>> getReturnCarRequest()
	{
		List<car1> cars;
		cars=this.cars;
		HashMap<String, List<car1>> listofcardetail = new HashMap<>();
		String key = "cardetailwithimage";
		listofcardetail.put(key, cars);
		return listofcardetail;
		
	}
	
	
	@RequestMapping(value = "/updatecarinportal/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public void  updatecarinportal(@PathVariable("id") int id)
	{
		System.out.print("hello jinal");
    	car1 car=c.findOne(id);
    	carbooking carbooking=cbr.getbookingdata(id);
    CustomerCarBooking custcarbook=car.getCustcarbook();
    Date ownerdropoff=carbooking.getDropoffdate();
   Date custdropoff= custcarbook.getDropoffDate();
   if(custdropoff.compareTo(ownerdropoff)==0)
   {
	   car.setisActive("false");
	   car.setInPortal("false");
   }
   else 
   {
	   java.sql.Date tomorrow= new java.sql.Date( custdropoff.getTime() + 24*60*60*1000);
	   carbooking.setTemppickupdate(tomorrow); 
	   carbooking.setTempdropoffdate(ownerdropoff);
	   cbr.save(carbooking);
   car.setisActive("false");
   car.setInPortal("true");
   c.save(car);
   }
		
	}
	@RequestMapping(value = "/saveimage3", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> imagestore1(@RequestParam("file") MultipartFile uploadfile) {
		try {
			// Get the filename and build the local file path
			String filename = uploadfile.getOriginalFilename();
			System.out.print("hellloooooooooo" + filename);
			// String directory = env.getProperty("com.carapp.carapplication.images");
			String directory ="E://easy2go//src//main//resources//static//pics";
			// UserController uc=new UserController();
			// int uid=uc.getUser();
			System.out.print("hellloooooooooo");
			String filepath = Paths.get(directory, filename).toString();
		//	String filepath="E:/"+"/easy2go/"+"/src/"+"/main/"+"/resouces/"+"/static/"+"/pics/"+"/"+filename;
			System.out.println(filepath);
			

			/*
			 * String filepath="C:\\Users\\JINAL\\Desktop\\i3.png"; String
			 * filepath1="E:\\images\\i4.png"; FileInputStream fis=new
			 * FileInputStream(filepath); FileOutputStream fos=new
			 * FileOutputStream(filepath1); int content; while ((content = fis.read()) !=
			 * -1) {
			 * 
			 * fos.write(content); } fis.close(); fos.close();
			 */

			// Save the file locally
			System.out.print("hellloooooooooo");
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filepath)));
			stream.write(uploadfile.getBytes());
			stream.close();
	
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(HttpStatus.OK);
	}
	@RequestMapping(value ="/getfilter/{id}", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, List<CarDto>> listoffiltercar(@PathVariable("id") String id) 
	{
		
		String name[]=id.split(",");
		String location=name[0];
		String brand=name[1];
		String model=name[2];
		int year=Integer.parseInt(name[3]);
		int price=Integer.parseInt(name[4]);
		List<CarDto> cardetails=carservice.getfiltercars(location,brand,model,year,price);
		HashMap<String, List<CarDto>> listofcardetail = new HashMap<>();
		String key = "cardetailwithimage";
		listofcardetail.put(key, cardetails);
		return listofcardetail;
	

	}
	@RequestMapping(value ="/getsearchcar/", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, List<CarDto>> listofsearchcar(@RequestBody carbooking carbooking) 
	{
		
		
		List<CarDto> cardetails=carservice.getsearchcars(carbooking);
		HashMap<String, List<CarDto>> listofcardetail = new HashMap<>();
		String key = "cardetailwithimage";
		listofcardetail.put(key, cardetails);
		return listofcardetail;
	

	}
	@RequestMapping(value = "/getQRcode")
	public void getQRcode() {

		try {
			String QR_CODE_IMAGE_PATH = "C:\\Users\\JINAL\\Desktop\\qr-code.png";
			String text = "";
			int width = 350, height = 350;
			String filePath = QR_CODE_IMAGE_PATH;
			QRCodeWriter qrCodeWriter = new QRCodeWriter();
			BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

			Path path = FileSystems.getDefault().getPath(filePath);
			MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
			// acser.saveaccountdetails(accountdetails);
			System.out.println("QR code generated");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@RequestMapping(value = "/getbookingdetails", method = RequestMethod.GET)
	public void listofcar(@RequestBody carbooking carbooking) {

		carservice.getcarid(carbooking);

	}

	@RequestMapping(value = "/getbookdeatailsforreceipt/{id}", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, BookingDto> bookingreceipt(@PathVariable("id") int id) {
		BookingDto dto = new BookingDto();
		int carid = id;
		System.out.println(carid);
		carbooking a = cbr.getbookingdata(carid);
		dto.setCarbooking(a);
		System.out.println(a.getBookingId());
		System.out.println(a.getDropofflocation());
		car1 b = a.getCar1();
		User c = b.getUser();
		System.out.println(c.getUsername());
		dto.setOwnername(c.getUserName());
		HashMap<String, BookingDto> listofcardetail = new HashMap<>();
		String key = "cardetailwithimage";
		listofcardetail.put(key, dto);
		return listofcardetail;

	}

	@RequestMapping(value = "/saveCar1", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<car1> initial123(@RequestBody car1 car) {
		 
		 User user=(User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 car.setUser(user);
		car.setCarfeature(feaid);
		System.out.print("heeeeeeeeeeellooooooooo"+feaid);
		c.save(car);
		this.car=car;
		
		/*
		 * List<car> listOfCars=c.findAll();
		 * 
		 * for( car cars : listOfCars) {
		 * 
		 * System.out.println(cars.getCompanyName()); }
		 */

		return new ResponseEntity<car1>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/convertpdf/{i}", method = RequestMethod.POST)
	@ResponseBody
	public String convertpdf(@PathVariable("i") String na) throws Exception
	{
		
		 try{
			 String values[]=na.split(",");
			 String value = "name"+values[0]+"\nprice"+values[1]+"\ncompanyname"+values[2]+"\npickupdate"+values[3]+"\ndropoofdate"+values[4];
			    FileOutputStream fos = new FileOutputStream("E://report.txt");
			    DataOutputStream outStream = new DataOutputStream(new BufferedOutputStream(fos));
			    outStream.writeUTF(value);
			    outStream.close();
			 
			    // verify the results
			    String result;
			    FileInputStream fis = new FileInputStream("E://report.txt");
			    DataInputStream reader = new DataInputStream(fis);
			    result = reader.readUTF();
			    reader.close();
			 
			   
//		      InputStreamReader in= new InputStreamReader(System.in);
//		      BufferedReader bin= new BufferedReader(in);
//		      System.out.println("Enter text:");
//		        String text=bin.readLine();
		        Document document = new Document(PageSize.A4, 36, 72, 108, 180);
		        PdfWriter.getInstance(document,new FileOutputStream("E://pdfFile.pdf"));
		        document.open();
		        document.add(new Paragraph(result));
		        System.out.println("Text is inserted into pdf file");
		       // getDownloadData("E://pdfFile.pdf");
		        document.close();
		   
		    }catch(Exception e){}
		 String na1="E://pdfFile.pdf";
		 return na1;
//		try {
//		    String k = "<html><body> This is my Project  </body></html>";
//		    OutputStream file = new FileOutputStream(new File("E:\\Test.pdf"));
//		    Document document = new Document();
//		    PdfWriter.getInstance(document, file);
//		    document.open();
//		    HTMLWorker htmlWorker = new HTMLWorker(document);
//		    htmlWorker.parse(new StringReader(k));
//		    document.close();
//		    file.close();
//		} catch (Exception e) {
//		    e.printStackTrace();
//		}
//		String inputFile = "D://Html//msc.xhtml";
//		  String url = new File(inputFile).toURI().toURL().toString();
//		  String outputFile = "D://Html//secondsdoc.pdf";
//		  OutputStream os = new FileOutputStream(outputFile);
//
//		  ITextRenderer renderer = new ITextRenderer();
//		  renderer.setDocument(url);
//		  renderer.layout();
//		  renderer.createPDF(os);
//
//		  os.close();
	}
	@RequestMapping(value = "/pdf", method = RequestMethod.GET)

	public void pdf() throws Exception
	{
		
			   
	}
	
	@RequestMapping(value = "/download", method = RequestMethod.GET)

	public void getDownloadData(HttpServletResponse response ) throws Exception
	{
		
		File	file = new File("E://pdfFile.pdf");
//			 HttpHeaders respHeaders = new HttpHeaders();
//			 respHeaders.setContentType(MediaType.APPLICATION_PDF);
//			    respHeaders.setContentLength(12345678);
//			    respHeaders.setContentDispositionFormData("attachment", "fileNameIwant.pdf");

			   
	    InputStream in = new FileInputStream(file);
	  System.out.println("in download");
		    response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
		    response.setHeader("Content-Length", String.valueOf(file.length()));
		    FileCopyUtils.copy(in, response.getOutputStream());	   
	}
	@RequestMapping(value = "/donotification1")
	@ResponseBody
	public ResponseEntity<?> notification1() throws Exception {

		try {

			String password = "brxrehtxyvmsluru";
			String to = "jinal.140410107100@gmail.com";
			String sub = "ONE TIME PASSWORD";
			String msg = "Your varification code is : ";
			String host = "smtp.gmail.com";
			final String user = "jinal2596@gmail.com";// change accordingly

			// Get the session object
			Properties props = new Properties();
			props.put("mail.smtp.host", host);

			
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.starttls.enable", "true");

			Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(user, password);
				}
			});
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject(sub);
			message.setText(msg);
			BodyPart messageBodyPart1 = new MimeBodyPart();  
		    messageBodyPart1.setText("This is message body");  
		      
		    //4) create new MimeBodyPart object and set DataHandler object to this object      
		    MimeBodyPart messageBodyPart2 = new MimeBodyPart();  
		  
		    String filename = "C:\\Users\\JINAL\\Desktop\\sem 6.pdf";//change accordingly  
		    DataSource source = new FileDataSource(filename);  
		    messageBodyPart2.setDataHandler(new DataHandler(source));  
		    messageBodyPart2.setFileName(filename);  
		     
		     
		    //5) create Multipart object and add MimeBodyPart objects to this object      
		    Multipart multipart = new MimeMultipart();  
		    multipart.addBodyPart(messageBodyPart1);  
		    multipart.addBodyPart(messageBodyPart2);  
		  
		    //6) set the multiplart object to the message object  
		    message.setContent(multipart );  
		     
		    //7) send message  
		    Transport.send(message);  
			// send the message
			Transport.send(message);
			System.out.println("mail send successfully....");
			

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		/*
		 * 
		 * JavaMailSender emailSender = new JavaMailSenderImpl();
		 * 
		 * SimpleMailMessage mail=new SimpleMailMessage();
		 * mail.setTo("jinal2596@gmail.com");
		 * mail.setFrom("jinal.140410107100@gmail.com"); mail.setSubject("hello");
		 * mail.setText("hi");
		 * 
		 * emailSender.send(mail);
		 */

		return new ResponseEntity<>(HttpStatus.OK);
	}


	@RequestMapping(value = "/donotification")
	@ResponseBody
	public ResponseEntity<?> notification(String to,String sub,String msg,String user) throws Exception {

		try {

			String password = "brxrehtxyvmsluru";
			//String to = "jinal.140410107100@gmail.com";
			//String sub = "ONE TIME PASSWORD";
			//String msg = "Your varification code is : ";
			String host = "smtp.gmail.com";
			//final String user = "jinal2596@gmail.com";// change accordingly

			// Get the session object
			Properties props = new Properties();
			props.put("mail.smtp.host", host);

			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.starttls.enable", "true");

			Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(user, password);
				}
			});
			  
				     MimeMessage message = new MimeMessage(session);  
				     message.setFrom(new InternetAddress(user));  
				     message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
				     message.setSubject(sub);  
				     message.setText(msg);  
				       
				    //send the message  
				     Transport.send(message);  
			Transport.send(message);
			System.out.println("mail send successfully....");
			

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		/*
		 * 
		 * JavaMailSender emailSender = new JavaMailSenderImpl();
		 * 
		 * SimpleMailMessage mail=new SimpleMailMessage();
		 * mail.setTo("jinal2596@gmail.com");
		 * mail.setFrom("jinal.140410107100@gmail.com"); mail.setSubject("hello");
		 * mail.setText("hi");
		 * 
		 * emailSender.send(mail);
		 */

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/savefeature", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<car1> feature(@RequestBody carfeature cf) {
		cFR.save(cf);
		feaid=cf;
		/*
		 * List<car> listOfCars=c.findAll();
		 * 
		 * for( car cars : listOfCars) {
		 * 
		 * System.out.println(cars.getCompanyName()); }
		 */

		return new ResponseEntity<car1>(HttpStatus.OK);
	}

	@RequestMapping(value = "/saveLicense", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<ownerslicensedeatails> initial(@RequestBody ownerslicensedeatails o) {

		a.save(o);
		/*
		 * List<car> listOfCars=c.findAll();
		 * 
		 * for( car cars : listOfCars) {
		 * 
		 * System.out.println(cars.getCompanyName()); }
		 */

		return new ResponseEntity<ownerslicensedeatails>(HttpStatus.OK);
	}

	@RequestMapping(value = "/saveBooking", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<ownerslicensedeatails> initialBooking(@RequestBody carbooking cb) {
			cb.setCar1(car);
		cbr.save(cb);
		/*
		 * List<car> listOfCars=c.findAll();
		 * 
		 * for( car cars : listOfCars) {
		 * 
		 * System.out.println(cars.getCompanyName()); }
		 */

		return new ResponseEntity<ownerslicensedeatails>(HttpStatus.OK);
	}

	@RequestMapping(value = "/getImages/{id} ", method = RequestMethod.GET)
	@ResponseBody
	public byte[] getImages(@PathVariable("id") String na) {

		System.out.print(na);
		File file = new File("E:\\images\\" + na);

		// create FileInputStream which obtains input bytes from a file in a file system
		// FileInputStream is meant for reading streams of raw bytes such as image data.
		// For reading streams of characters, consider using FileReader.

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] buf = new byte[1024];
		try {
			FileInputStream fis = new FileInputStream(file);
			for (int readNum; (readNum = fis.read(buf)) != -1;) {
				// Writes to this byte array output stream
				bos.write(buf, 0, readNum);
				System.out.println("read " + readNum + " bytes,");
			}
		} catch (IOException ex) {
			// Logger.getLogger(Convert.class.getName()).log(Level.SEVERE, null, ex);
		}

		byte[] bytes = bos.toByteArray();
		// int len=bytes.length;

		// myBytes.add(bytes);

		// System.out.println("jinal"+bytes);

		return bytes;

		/*
		 * ByteArrayInputStream bis = new ByteArrayInputStream(bytes); Iterator<?>
		 * readers = ImageIO.getImageReadersByFormatName("png");
		 * 
		 * //ImageIO is a class containing static methods for locating ImageReaders
		 * //and ImageWriters, and performing simple encoding and decoding.
		 * 
		 * ImageReader reader = (ImageReader) readers.next(); Object source = bis; try {
		 * 
		 * 
		 * ImageInputStream iis = ImageIO.createImageInputStream(source);
		 * reader.setInput(iis, true); ImageReadParam param =
		 * reader.getDefaultReadParam();
		 * 
		 * Image image = reader.read(0, param); //got an image file
		 * 
		 * BufferedImage bufferedImage = new BufferedImage(image.getWidth(null),
		 * image.getHeight(null), BufferedImage.TYPE_INT_RGB); //bufferedImage is the
		 * RenderedImage to be written
		 * 
		 * Graphics2D g2 = bufferedImage.createGraphics(); g2.drawImage(image, null,
		 * null);
		 * 
		 * File imageFile = new File("E:\\newrose2.png"); ImageIO.write(bufferedImage,
		 * "png", imageFile);
		 * 
		 * System.out.println(imageFile.getPath()); } catch( Exception e) {
		 * 
		 * }
		 */

	}

	@RequestMapping(value = "/getcars", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, List<CarDto>> getCars() {
		List<CarDto> cardetails;

		cardetails = carservice.findAllAuthFcar();

		HashMap<String, List<CarDto>> listofcardetail = new HashMap<>();
		String key = "cardetailwithimage";
		listofcardetail.put(key, cardetails);
		return listofcardetail;

	}
	
	
	
	
	
	@RequestMapping(value = "/getcarhistoryforcurrentuser", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, List<CarDto>> getCarHistoryForCurrentUser() {
		List<CarDto> cardetails;
		 User user=(User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 System.out.println("heelooo"+user);
		 int id=user.getUserid();
		cardetails = carservice.findHistoryCar(id);

		HashMap<String, List<CarDto>> listofcardetail = new HashMap<>();
		String key = "cardetailwithimage";
		listofcardetail.put(key, cardetails);
		return listofcardetail;

	}
	@RequestMapping(value = "/getcarswithauthentication", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, List<CarDto>> getCarswithauthentication() {
		List<CarDto> cardetails;

		cardetails = carservice.findAllAuthcar();

		HashMap<String, List<CarDto>> listofcardetail = new HashMap<>();
		String key = "cardetailwithimage";
		listofcardetail.put(key, cardetails);
		return listofcardetail;

	}


	@RequestMapping(value = "/comparecars/{name}", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, List<CarDto>> getThreeCars(@PathVariable("name") String name) {
		System.out.print(name);
		String a[] = name.split(",");

		System.out.print(a[0] + a[1] + a[2]);
		String name1 = a[0];
		String name2 = a[1];
		String name3 = a[2];
		List<CarDto> cardetails;
		cardetails = carservice.findThreeCar(name1, name2, name3);

		HashMap<String, List<CarDto>> listofcardetail = new HashMap<>();
		String key = "cardetailwithimage";
		listofcardetail.put(key, cardetails);
		return listofcardetail;

	}

	@RequestMapping(value = "/list")
	public String getOneCar1() {

		System.out.println("\n\n\n\n\n\n hellllllllllllllllloooooooooooooo");
		return "listing-detail";

	}

	@RequestMapping(value = "/getonecar/{id}", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, CarDto> getOneCar(@PathVariable("id") int id) {

		CarDto cardetails1;
		HashMap<String, CarDto> cardetails = new HashMap<>();
		System.out.print("calling the method");
		String key = "cardetailwithimage1";
		cardetails1 = carservice.findOnecar(id);
		cardetails.put(key, cardetails1);
		System.out.println("doneee" + cardetails);
		return cardetails;

	}

	@RequestMapping(value = "/updateonecar/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<car1> updateCar(@PathVariable("id") int id, @RequestBody car1 car) {
		car1 currentcar = c.findOne(id);
		System.out.println("INSIDE UPDATE FUNCTION");
		if (currentcar == null) {
			System.out.println("User with id " + id + " not found");
			return new ResponseEntity<car1>(HttpStatus.NOT_FOUND);
		}
		System.out.println("INSIDE UPDATE FUNCTION");
		currentcar.setisAuthenticated(car.getisAuthenticated());
		System.out.print(currentcar.getisAuthenticated());
		car1 car1=c.finduser(id);
		User u=car1.getUser();
		if(currentcar.getisAuthenticated().equals("true"))
		{
			
	String to=	u.getEmailid();
		//String to="jinal.140410107100@gmail.com";
		String sub="Regarding add new car into portal";
		String msg="your car has been successfully added to our easy2go portal ";
		String from="jinal2596@gmail.com";
		try
		{
		notification(to,sub,msg,from);
		}
		catch(Exception e)
		{
			
		}}
		else
		{
			String to=	u.getEmailid();
				//String to=user.getEmailid();
			//	String to="jinal.140410107100@gmail.com";
				String sub="Regarding add new car into portal";
				String msg="your car has not been  added to our easy2go portal because of some verification issues";
				String from="jinal2596@gmail.com";
				try
				{
				notification(to,sub,msg,from);
				}
				catch(Exception e)
				{
					
				}	
		}
		c.save(currentcar);
		System.out.println("INSIDE UPDATE FUNCTION");
		
		return new ResponseEntity<car1>(currentcar, HttpStatus.OK);
	}

	// @RequestMapping(value="/dosql")
	// @ResponseBody
	/*
	 * public void doSql() { User A= new User(); A.setUsername("jinal");
	 * A.setMobilenumber("99887766554"); A.setEmailid("jinal2596@gmail.com");
	 * A.setPassword("hello"); A.setConfirmpassword("hello"); User B=new User(A);
	 * 
	 * System.out.println("before");
	 * 
	 * System.out.println("before"); Set caras = new HashSet<car1>(){{ add(new
	 * car1(22,33,44,55,B)); add(new car1(55,44,33,22,B)); add(new
	 * car1(22,33,44,55,B)); }}; System.out.println("before"); B.setCars(caras);
	 * System.out.println("before"); BookCategory categoryB = new
	 * BookCategory("Category B"); Set bookBs = new HashSet<Book>(){{ add(new
	 * Book("Book B1", categoryB)); add(new Book("Book B2", categoryB)); add(new
	 * Book("Book B3", categoryB)); }}; categoryB.setBooks(bookBs);
	 * 
	 * uR.save(new HashSet<User>() {{ add(B); //add(categoryB); }});
	 * System.out.println("before");
	 * 
	 * }
	 */

	@RequestMapping(value = "/saveimage", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> imagestore(@RequestParam("file") MultipartFile uploadfile) {
		try {
			// Get the filename and build the local file path
			String filename = uploadfile.getOriginalFilename();
			System.out.print("hellloooooooooo" + filename);
			// String directory = env.getProperty("com.carapp.carapplication.images");
			String directory ="E://easy2go//src//main//resources//static//pics";
			// UserController uc=new UserController();
			// int uid=uc.getUser();
			System.out.print("hellloooooooooo");
		String filepath = Paths.get(directory, filename).toString();
			//String filepath="E:/"+"/easy2go/"+"/src/"+"/main/"+"/resouces/"+"/static/"+"/pics/"+"/"+filename;
			System.out.println(filepath);
			CarImages i1 = new CarImages();
			i1.setCar1(car);
			i1.setImagepath("pics/"+filename);// +"_"+uid);
			iR.save(i1);

			/*
			 * String filepath="C:\\Users\\JINAL\\Desktop\\i3.png"; String
			 * filepath1="E:\\images\\i4.png"; FileInputStream fis=new
			 * FileInputStream(filepath); FileOutputStream fos=new
			 * FileOutputStream(filepath1); int content; while ((content = fis.read()) !=
			 * -1) {
			 * 
			 * fos.write(content); } fis.close(); fos.close();
			 */

			// Save the file locally
			System.out.print("hellloooooooooo");
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filepath)));
			stream.write(uploadfile.getBytes());
			stream.close();
			 User u=(User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String to= u.getEmailid();
			//String to="jinal.140410107100@gmail.com";
			String sub="car verification";
			String msg="your car has been successfully sent to admin for verification";
			String from="jinal2596@gmail.com";
			
			notification(to,sub,msg,from);
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(HttpStatus.OK);
	}

}
