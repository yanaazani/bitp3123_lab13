package server;

public class TextGenerator {
	public static int wordCount(String text) {
		if (text==null || text.isEmpty()) {
			return 0;
		}
		
		String[] words = text.split("\\s+");
		return words.length;
	}
	
	public static void main(String[] args) {
        String text = "Hello, how are you?";
        int wordCount = 0;
		try {
			wordCount = wordCount(text);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("Word count: " + wordCount);
    }
}
