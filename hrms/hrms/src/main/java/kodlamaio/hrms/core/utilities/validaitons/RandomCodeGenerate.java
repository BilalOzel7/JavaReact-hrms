package kodlamaio.hrms.core.utilities.validaitons;

import java.util.Random;

public class RandomCodeGenerate {

	public String create() {
		int leftLimit = 1;
        int rightLimit = 200;
        int targetStringLength = 15;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
	}
}
