import static org.junit.Assert.*;

import org.junit.Test;


public class BuildingTest {

	@Test
	public void testSetters() {
		
		Building b = new Building();
		b.setAbsUrl("abc");
		assertEquals("abc", b.getAbsUrl());
		
		b.setThumbUrl("abfd");
		assertEquals("abfd", b.getThumbUrl());
		
		b = new Building("absurl");
		assertEquals("absurl", b.getAbsUrl());
		assertNull(b.getThumbUrl());
		
		b = new Building("123", "thumb");
		assertEquals("123", b.getAbsUrl());
		assertEquals("thumb", b.getThumbUrl());
		
	}

}
