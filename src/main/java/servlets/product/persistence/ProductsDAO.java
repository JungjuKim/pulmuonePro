package servlets.product.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jdbc.JdbcUtil;
import servlets.product.domain.ProductsDTO;

public class ProductsDAO implements IProducts {
	// 1. 싱글톤
	private ProductsDAO() {}
	private static ProductsDAO instance = new ProductsDAO();
	public static ProductsDAO getInstance() {
		return instance;
	}
	@Override
	public List<ProductsDTO> select(Connection con,String path) throws SQLException {
		String sql =
				" select a.products_no, category_no, products_name, products_sub_name, products_type, content, price, event_price"
						+ ", products_size, delivery_type, tag_no1, tag_no2, tag_no3, tag_no4, tag_no5, products_tag, reg_date, system_name "	              
						+ " from products a join products_img b on a.products_no = b.products_no "
						+ " where delivery_type = ? "
						+ " and origin_name not like 'View%' "
						+ " order by img_no ";
		ArrayList<ProductsDTO> list = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;	        
		try {
			pstmt = con.prepareStatement(sql);
			//	           System.out.println("select");
			//	           System.out.println(sql);
			pstmt.setString(1, path);
			rs = pstmt.executeQuery();
			if ( rs.next() ) {
				list = new ArrayList<ProductsDTO>();
				ProductsDTO dto = null;
				do {
					dto =  new ProductsDTO();
					dto.setProducts_no(rs.getString("products_no"));
					dto.setCategory_no(rs.getString("category_no"));
					dto.setProducts_name(rs.getString("products_name"));
					dto.setProducts_sub_name(rs.getString("products_sub_name"));
					dto.setProducts_type(rs.getString("products_type"));
					dto.setContent(rs.getString("content"));
					dto.setPrice(rs.getInt("price"));
					dto.setEvent_price(rs.getInt("event_price"));
					dto.setProducts_size(rs.getString("products_size"));	                 	
					dto.setDelivery_type(rs.getString("delivery_type"));	                 	
					dto.setTag_no1(rs.getInt("tag_no1"));
					dto.setTag_no2(rs.getInt("tag_no2"));
					dto.setTag_no3(rs.getInt("tag_no3"));
					dto.setTag_no4(rs.getInt("tag_no4"));
					dto.setTag_no5(rs.getInt("tag_no5"));
					dto.setProducts_tag(rs.getShort("products_tag"));
					dto.setReg_date(rs.getDate("reg_date"));
					dto.setSystem_name(rs.getString("system_name"));
					list.add(dto);
				} while ( rs.next() );
			} // 
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);         
		} // finally

		return list;
	}	
	@Override
	public List<ProductsDTO> selectbest(Connection con, String path) throws SQLException {
		String sql =
				" select a.products_no, category_no, products_name, products_sub_name, products_type, content, price, event_price"
						+ ", products_size, delivery_type, tag_no1, tag_no2, tag_no3, tag_no4, tag_no5, products_tag, reg_date, system_name "	              
						+ " from products a join products_img b on a.products_no = b.products_no "
						+ " where delivery_type = ? ";
		switch (path) {
		case "daily":
			//				sql += " and a.products_no in (0073080,0070680,0073405,0071928,0073019,0071654,0070776,0073344,0072840,0073184,0073271,0073325,0073327,0073274,0070800,0070445,0073156,0073155,0071664,0072652) and origin_name not like 'View%' ";
			sql += " and a.products_no in (0071654,0070776,0073405,0073184,0073325,0073019,0073344,0071664,0071928,0073156,0073327,0070680,0070445,0073080,0072652,0073155,0073274,0070800,0072840,0073271) and origin_name not like 'View%' ";				
			break;
		case "box":
			sql += " and a.products_no in (0073561,0072964,0073077,0072905,0073166,0072965,0073190,0072906,0073136,0073326,0072966,0073165,0071968,0073324 ) and origin_name not like 'View%' ";
			break;
		}	       
		ArrayList<ProductsDTO> dailylist = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;	        
		try {
			pstmt = con.prepareStatement(sql);	
			//	           System.out.println("selectbest");
			//	           System.out.println(sql);
			pstmt.setString(1, path);
			rs = pstmt.executeQuery();
			if ( rs.next() ) {
				dailylist = new ArrayList<ProductsDTO>();
				ProductsDTO dto = null;
				do {
					dto =  new ProductsDTO();
					dto.setProducts_no(rs.getString("products_no"));
					dto.setCategory_no(rs.getString("category_no"));
					dto.setProducts_name(rs.getString("products_name"));
					dto.setProducts_sub_name(rs.getString("products_sub_name"));
					dto.setProducts_type(rs.getString("products_type"));
					dto.setContent(rs.getString("content"));
					dto.setPrice(rs.getInt("price"));
					dto.setEvent_price(rs.getInt("event_price"));
					dto.setProducts_size(rs.getString("products_size"));	                 	
					dto.setDelivery_type(rs.getString("delivery_type"));	                 	
					dto.setTag_no1(rs.getInt("tag_no1"));
					dto.setTag_no2(rs.getInt("tag_no2"));
					dto.setTag_no3(rs.getInt("tag_no3"));
					dto.setTag_no4(rs.getInt("tag_no4"));
					dto.setTag_no5(rs.getInt("tag_no5"));
					dto.setProducts_tag(rs.getShort("products_tag"));
					dto.setReg_date(rs.getDate("reg_date"));
					dto.setSystem_name(rs.getString("system_name"));
					dailylist.add(dto);
				} while ( rs.next() );
			} // 
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);         
		} // finally

		return dailylist;
	}		
	@Override
	public List<ProductsDTO> searchcount(Connection con,String path, String tags) throws SQLException {
		String sql =
	              "  select a.products_no, category_no, products_name, products_sub_name, products_type, content, price, event_price"
	              + ", products_size, delivery_type, tag_no1, tag_no2, tag_no3, tag_no4, tag_no5, products_tag, reg_date, system_name "	              
	              + " from products a join products_img b on a.products_no = b.products_no "
	              + " where delivery_type = ? "
	              + " and origin_name not like 'View%' ";	  
	    if(tags!=null && tags != "") {			  	  
	          sql += String.format(" and( tag_no1 in (%s) ", tags);
	          sql += String.format(" or tag_no2 in (%s) ", tags);
	          sql += String.format(" or tag_no3 in (%s) ", tags);
	          sql += String.format(" or tag_no4 in (%s) ", tags);
	          sql += String.format(" or tag_no5 in (%s) ) ", tags);
		}				  	     
	        ArrayList<ProductsDTO> list = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;       	       	        		        
	        try {
	           pstmt = con.prepareStatement(sql);
	           pstmt.setString(1, path);		           
//	           System.out.println("searchcount");
//	           System.out.println(path);
//	           System.out.println(tags);
//	           System.out.println(sql);
	           rs = pstmt.executeQuery();	  
	           if ( rs.next() ) {	        	
	              list = new ArrayList<ProductsDTO>();
	              ProductsDTO dto = null;
	              do {
	                 dto =  new ProductsDTO();
	                 	dto.setProducts_no(rs.getString("products_no"));
	                 	dto.setCategory_no(rs.getString("category_no"));
	                 	dto.setProducts_name(rs.getString("products_name"));
	                 	dto.setProducts_sub_name(rs.getString("products_sub_name"));
	                 	dto.setProducts_type(rs.getString("products_type"));
	                 	dto.setContent(rs.getString("content"));
	                 	dto.setPrice(rs.getInt("price"));
	                 	dto.setEvent_price(rs.getInt("event_price"));
	                 	dto.setProducts_size(rs.getString("products_size"));	                 	
	                 	dto.setDelivery_type(rs.getString("delivery_type"));	                 	
	                 	dto.setTag_no1(rs.getInt("tag_no1"));
	                 	dto.setTag_no2(rs.getInt("tag_no2"));
	                 	dto.setTag_no3(rs.getInt("tag_no3"));
	                 	dto.setTag_no4(rs.getInt("tag_no4"));
	                 	dto.setTag_no5(rs.getInt("tag_no5"));
	                 	dto.setProducts_tag(rs.getShort("products_tag"));
	                 	dto.setReg_date(rs.getDate("reg_date"));
	                 	dto.setSystem_name(rs.getString("system_name"));	  
//	                 	System.out.println(dto);
	                 list.add(dto);
//	                 System.out.println(list);
	              } while ( rs.next() );
	           } // 
	        } finally {
	           JdbcUtil.close(pstmt);
	           JdbcUtil.close(rs);         
	        } // finally

	        return list;
	}
	@Override
	public List<ProductsDTO> search(Connection con,String path, String tags, String num) throws SQLException {
//		System.out.println(num);
		int begin = Integer.parseInt(num);
		if(begin==2) {
			begin = 13;
		}else if(begin==3) {
			begin = 25;
		}else if(begin==4) {
			begin = 37;
		}
		int end = begin+11;
		String sql = " SELECT products_no, category_no, products_name, products_sub_name, products_type, content, price, event_price "
				+ " , products_size, delivery_type, tag_no1, tag_no2, tag_no3, tag_no4, tag_no5, products_tag, reg_date, system_name "
				+ "FROM ( "
				+ "        SELECT ROWNUM no, t.* "
				+ "        FROM (  "
				+ "                select a.products_no, category_no, products_name, products_sub_name, products_type, content, price, event_price "
				+ "	              ,products_size, delivery_type, tag_no1, tag_no2, tag_no3, tag_no4, tag_no5, products_tag, reg_date, system_name "
				+ "	               from products a join products_img b on a.products_no = b.products_no "
				+ "	               where delivery_type = ? "
				+ "	               and origin_name not like 'View%' ";
				if(tags!=null && tags != "") {			  	  
			          sql += String.format(" and( tag_no1 in (%s) ", tags);
			          sql += String.format(" or tag_no2 in (%s) ", tags);
			          sql += String.format(" or tag_no3 in (%s) ", tags);
			          sql += String.format(" or tag_no4 in (%s) ", tags);
			          sql += String.format(" or tag_no5 in (%s) ) ", tags);
				}				
				sql+= "        ) t "
				+ ")  b "
				+ "WHERE b.no BETWEEN ? AND ? ";	 	 
	    					    
	        ArrayList<ProductsDTO> list = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;       	       	        		        
	        try {
	           pstmt = con.prepareStatement(sql);
	           pstmt.setString(1, path);		           
	           pstmt.setInt(2, begin);
	           pstmt.setInt(3, end);
	           System.out.println("search");
	           System.out.println(begin);
	           System.out.println(end);
	           System.out.println(sql);
	           rs = pstmt.executeQuery();	  
	           if ( rs.next() ) {	        	
	              list = new ArrayList<ProductsDTO>();
	              ProductsDTO dto = null;
	              do {
	                 dto =  new ProductsDTO();
	                 	dto.setProducts_no(rs.getString("products_no"));
	                 	dto.setCategory_no(rs.getString("category_no"));
	                 	dto.setProducts_name(rs.getString("products_name"));
	                 	dto.setProducts_sub_name(rs.getString("products_sub_name"));
	                 	dto.setProducts_type(rs.getString("products_type"));
	                 	dto.setContent(rs.getString("content"));
	                 	dto.setPrice(rs.getInt("price"));
	                 	dto.setEvent_price(rs.getInt("event_price"));
	                 	dto.setProducts_size(rs.getString("products_size"));	                 	
	                 	dto.setDelivery_type(rs.getString("delivery_type"));	                 	
	                 	dto.setTag_no1(rs.getInt("tag_no1"));
	                 	dto.setTag_no2(rs.getInt("tag_no2"));
	                 	dto.setTag_no3(rs.getInt("tag_no3"));
	                 	dto.setTag_no4(rs.getInt("tag_no4"));
	                 	dto.setTag_no5(rs.getInt("tag_no5"));
	                 	dto.setProducts_tag(rs.getShort("products_tag"));
	                 	dto.setReg_date(rs.getDate("reg_date"));
	                 	dto.setSystem_name(rs.getString("system_name"));	  
	                 list.add(dto);
	              } while ( rs.next() );
	           } // 
	        } finally {
	           JdbcUtil.close(pstmt);
	           JdbcUtil.close(rs);         
	        } // finally
	        return list;
	}
//		@Override
//		public List<ProductsDTO> search(Connection con,String path, String tags, String num) throws SQLException {
//			String sql =
//		              "  select a.products_no, category_no, products_name, products_sub_name, products_type, content, price, event_price"
//		              + ", products_size, delivery_type, tag_no1, tag_no2, tag_no3, tag_no4, tag_no5, products_tag, reg_date, system_name "	              
//		              + " from products a join products_img b on a.products_no = b.products_no "
//		              + " where delivery_type = ? "
//		              + " and origin_name not like 'View%' ";	  
//		    if(tags!=null && tags != "") {			  	  
//		          sql += String.format(" and( tag_no1 in (%s) ", tags);
//		          sql += String.format(" or tag_no2 in (%s) ", tags);
//		          sql += String.format(" or tag_no3 in (%s) ", tags);
//		          sql += String.format(" or tag_no4 in (%s) ", tags);
//		          sql += String.format(" or tag_no5 in (%s) ) ", tags);
//			}					    
//			switch (num) {
//			case "1":
//				sql += "and ROWNUM <=12 ";
//				break;
//			case "2": //제목
//				sql += "and ROWNUM <=24 ";
//				break;
//			case "3": //제목
//				sql += "and ROWNUM <=36 ";
//				break;
//			case "4": //제목
//				sql += "and ROWNUM <=48 ";
//				break;
//			}
//		        ArrayList<ProductsDTO> list = null;
//		        PreparedStatement pstmt = null;
//		        ResultSet rs = null;       	       	        		        
//		        try {
//		           pstmt = con.prepareStatement(sql);
//		           pstmt.setString(1, path);		           
//		           System.out.println("search");
////		           System.out.println(path);
////		           System.out.println(tags);
//		           System.out.println(sql);
//		           rs = pstmt.executeQuery();	  
//		           if ( rs.next() ) {	        	
//		              list = new ArrayList<ProductsDTO>();
//		              ProductsDTO dto = null;
//		              do {
//		                 dto =  new ProductsDTO();
//		                 	dto.setProducts_no(rs.getString("products_no"));
//		                 	dto.setCategory_no(rs.getString("category_no"));
//		                 	dto.setProducts_name(rs.getString("products_name"));
//		                 	dto.setProducts_sub_name(rs.getString("products_sub_name"));
//		                 	dto.setProducts_type(rs.getString("products_type"));
//		                 	dto.setContent(rs.getString("content"));
//		                 	dto.setPrice(rs.getInt("price"));
//		                 	dto.setEvent_price(rs.getInt("event_price"));
//		                 	dto.setProducts_size(rs.getString("products_size"));	                 	
//		                 	dto.setDelivery_type(rs.getString("delivery_type"));	                 	
//		                 	dto.setTag_no1(rs.getInt("tag_no1"));
//		                 	dto.setTag_no2(rs.getInt("tag_no2"));
//		                 	dto.setTag_no3(rs.getInt("tag_no3"));
//		                 	dto.setTag_no4(rs.getInt("tag_no4"));
//		                 	dto.setTag_no5(rs.getInt("tag_no5"));
//		                 	dto.setProducts_tag(rs.getShort("products_tag"));
//		                 	dto.setReg_date(rs.getDate("reg_date"));
//		                 	dto.setSystem_name(rs.getString("system_name"));	  
//	//	                 	System.out.println(dto);
//		                 list.add(dto);
//	//	                 System.out.println(list);
//		              } while ( rs.next() );
//		           } // 
//		        } finally {
//		           JdbcUtil.close(pstmt);
//		           JdbcUtil.close(rs);         
//		        } // finally
//	
//		        return list;
//		}
//		@Override
//		public ProductsDTO view(Connection con, int tag) throws Exception {
//			String sql = "select a.products_no, category_no, products_name, products_sub_name, products_type, content, price, event_price, products_size, delivery_type, tag_no1, tag_no2, tag_no3, tag_no4, tag_no5, products_tag, reg_date, system_name,origin_name "
//					+ " from products a join products_img b on a.products_no = b.products_no "
//					+ " where products_tag = ?";
//			PreparedStatement pstmt = null;
//			ResultSet rs = null;
//			pstmt = con.prepareStatement(sql);
//			pstmt.setInt(1,tag);		
//			ProductsDTO dto = null;
//			rs = pstmt.executeQuery();
//			if(rs.next()) {
//				dto = ProductsDTO.builder()
//						.products_no(rs.getString("products_no"))
//						.products_name(rs.getString("products_name"))
//						.products_sub_name(rs.getString("products_sub_name"))										
//						.price(rs.getInt("price"))
//						.products_size(rs.getString("products_size"))
//						.products_tag(tag)
//						.system_name(rs.getString("system_name"))
//						.origin_name(rs.getString("origin_name"))
//						.content(rs.getString("content")).build();
//			}
//			rs.close();
//			pstmt.close();
//			return dto;   
//			
//		}
	@Override
	public List<ProductsDTO> viewlist(Connection con, int tag) throws Exception {
		String sql = "select a.products_no, category_no, products_name, products_sub_name, products_type, content, price, event_price, products_size, delivery_type, tag_no1, tag_no2, tag_no3, tag_no4, tag_no5, products_tag, reg_date, system_name,origin_name "
				+ " from products a join products_img b on a.products_no = b.products_no "
				+ " where products_tag = ? "
				+ " and origin_name like 'View%' ";
		ArrayList<ProductsDTO> list = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;	        
		try {
			pstmt = con.prepareStatement(sql);	           
			pstmt.setInt(1,tag);
			rs = pstmt.executeQuery();
			if ( rs.next() ) {
				list = new ArrayList<ProductsDTO>();
				ProductsDTO dto = null;
				do {
					dto =  new ProductsDTO();
					dto.setProducts_no(rs.getString("products_no"));
					dto.setCategory_no(rs.getString("category_no"));
					dto.setProducts_name(rs.getString("products_name"));
					dto.setProducts_sub_name(rs.getString("products_sub_name"));
					dto.setProducts_type(rs.getString("products_type"));
					dto.setContent(rs.getString("content"));
					dto.setPrice(rs.getInt("price"));
					dto.setEvent_price(rs.getInt("event_price"));
					dto.setProducts_size(rs.getString("products_size"));	                 	
					dto.setDelivery_type(rs.getString("delivery_type"));	                 	
					dto.setTag_no1(rs.getInt("tag_no1"));
					dto.setTag_no2(rs.getInt("tag_no2"));
					dto.setTag_no3(rs.getInt("tag_no3"));
					dto.setTag_no4(rs.getInt("tag_no4"));
					dto.setTag_no5(rs.getInt("tag_no5"));
					dto.setProducts_tag(rs.getShort("products_tag"));
					dto.setReg_date(rs.getDate("reg_date"));
					dto.setSystem_name(rs.getString("system_name"));
					dto.setOrigin_name(rs.getString("origin_name"));
					list.add(dto);
				} while ( rs.next() );
			} // 
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);         
		} // finally
		return list;

	}
	@Override
	public ProductsDTO view(Connection con, int tag) throws Exception {
		String sql = "select a.products_no, category_no, products_name, products_sub_name, products_type, content, price, event_price, products_size, delivery_type, tag_no1, tag_no2, tag_no3, tag_no4, tag_no5, products_tag, reg_date, system_name,origin_name "
				+ " from products a join products_img b on a.products_no = b.products_no "
				+ " where products_tag = ? "
				+ " and origin_name like 'View%' ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1,tag);
		ProductsDTO dto = null;
		System.out.println("view");
		System.out.println(sql);
		rs = pstmt.executeQuery();
		if(rs.next()) {
			dto = ProductsDTO.builder()
					.products_no(rs.getString("products_no"))
					.category_no(rs.getString("category_no"))
					.products_name(rs.getString("products_name"))
					.products_sub_name(rs.getString("products_sub_name"))
					.products_type(rs.getString("products_type"))
					.content(rs.getString("content"))
					.price(rs.getInt("price"))
					.event_price(rs.getInt("event_price"))
					.products_size(rs.getString("products_size"))
					.delivery_type(rs.getString("delivery_type"))
					.tag_no1(rs.getInt("tag_no1"))
					.tag_no2(rs.getInt("tag_no2"))
					.tag_no3(rs.getInt("tag_no3"))
					.tag_no4(rs.getInt("tag_no4"))
					.tag_no5(rs.getInt("tag_no5"))
					.products_tag(rs.getShort("products_tag"))
					.reg_date(rs.getDate("reg_date"))
					.system_name(rs.getString("system_name"))
					.origin_name(rs.getString("origin_name")).build();
		}
		rs.close();
		pstmt.close();
		return dto;
	}
}