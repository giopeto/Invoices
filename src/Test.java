import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import hibernateUtil.HibernateUtil;
import model.Invoice;
import model.Row;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class Test {
	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {
		Session session = HibernateUtil.openSession();
		Transaction transaction = null;	
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		
		/* DELETE */
		/* KILL ALL PROCESS
		select concat('KILL ',id,';') from information_schema.processlist where user='root' into outfile '/tmp/a.txt';
		source /tmp/a.txt;
		*/
		/*Query query = session.createQuery("delete Row where id > :id");
		Query query2 = session.createQuery("delete Invoice where id > :id");
		query.setParameter("id", new Long(0));
		query2.setParameter("id", new Long(0));
		
		int result = query.executeUpdate();
		int result2 = query2.executeUpdate();
		 
		if (result > 0 && result2 > 0) {
		    System.out.println("DELETEEEE");
		}*/
		
		

		/* SAVE AND FIRST GET */
		/*try {
			transaction = session.getTransaction();
			transaction.begin();

			Invoice invoice = new Invoice("INV000003");
			session.save(invoice);
			
			session.save(new Row(new Long(1), invoice));
			session.save(new Row(new Long(2), invoice));
			
			Invoice invoiceData = (Invoice) session.get(Invoice.class, new Long(18));
			
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}*/
		
		
		
		/* GET */
		
		Session session2 = HibernateUtil.openSession();
		
		Invoice invoiceData2 = (Invoice) session2.get(Invoice.class, new Long(18));
		
		
		System.out.println(invoiceData2);
		System.out.println("TO JSON");
		System.out.println(ow.writeValueAsString(invoiceData2));
		System.out.println("INVOICE ID: " + invoiceData2.getId());
		System.out.println("INVOICE NUM: " + invoiceData2.getNum());
		invoiceData2.getRow().forEach(e -> {
			System.out.println("ROW ID: " + e.getId());
			System.out.println("ITEM ID: " + e.getItemId());
			System.out.println("Invoice: " + e.getInvoice().getNum() );
		});
		
		
		Query query1 = session.createQuery("from Invoice");
		List<Invoice> allInvoice = new ArrayList();
		allInvoice = (List<Invoice>) query1.list();
		
		System.out.println(ow.writeValueAsString(allInvoice));
		List<Invoice> newInvoice = new ArrayList();
		newInvoice = (List<Invoice>) allInvoice.stream().
			filter(e-> e.getNum() != "").
			filter(e-> e.getNum().equalsIgnoreCase("INV000003") ).
			limit(2).
			collect( Collectors.toList() );     
		;
		
		System.out.println("NEW INVOICE: ");
		System.out.println(ow.writeValueAsString(newInvoice));
		
		
	}
}