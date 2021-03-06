package net.jcip.examples;

import net.jcip.annotations.ThreadSafe;

import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Arrays;

@ThreadSafe
public class StatelessFactorizer extends GenericServlet implements Servlet {

	@Override
	public void service(ServletRequest req, ServletResponse res) throws IOException {
		BigInteger i = extractFromRequest(req);
		BigInteger[] factors = factor(i);
		encodeIntoResponse(res, factors);
	}

	void encodeIntoResponse(ServletResponse res, BigInteger[] factors) throws IOException {
		res.setContentType("text/html");
		PrintWriter printWriter = res.getWriter();
		printWriter.print("<html>");
		printWriter.print("<body>");
		printWriter.print("<h2> Stateless " + Arrays.toString(factors) + "</h2>");
		printWriter.print("</body>");
		printWriter.print("</html>");
	}

	BigInteger extractFromRequest(ServletRequest req) {
		return new BigInteger("7");
	}

	BigInteger[] factor(BigInteger i) {
		return new BigInteger[]{i};
	}
}
