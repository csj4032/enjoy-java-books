package net.jcip.examples;

import net.jcip.annotations.NotThreadSafe;

import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Arrays;

@NotThreadSafe
public class UnsafeCountingFactorizer extends GenericServlet implements Servlet {
	private long count = 0;

	public long getCount() {
		return count;
	}

	public void service(ServletRequest req, ServletResponse resp) throws IOException {
		BigInteger i = extractFromRequest(req);
		BigInteger[] factors = factor(i);
		++count;
		encodeIntoResponse(resp, factors);
	}

	void encodeIntoResponse(ServletResponse res, BigInteger[] factors) throws IOException {
		res.setContentType("text/html");
		PrintWriter printWriter = res.getWriter();
		printWriter.print("<html>");
		printWriter.print("<body>");
		printWriter.print("<h2> UnsafeCounting " + Arrays.toString(factors) + "</h2>");
		printWriter.print("<h2> UnsafeCounting " + count + "</h2>");
		printWriter.print("</body>");
		printWriter.print("</html>");
	}

	BigInteger extractFromRequest(ServletRequest req) {
		return new BigInteger("7");
	}

	BigInteger[] factor(BigInteger i) {
		// Doesn't really factor
		return new BigInteger[] { i };
	}
}