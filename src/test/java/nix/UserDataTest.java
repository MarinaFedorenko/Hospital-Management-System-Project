package nix;

import nix.servlet.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserDataTest extends Mockito {

    @Test
    public void testAddAppointmentServletDoGet() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        AddAppointmentServlet servlet = new AddAppointmentServlet();
        when(request.getRequestDispatcher(Links.APPOINTMENT_JSP)).thenReturn(dispatcher);

        servlet.doGet(request, response);

        verify(request, times(1)).getRequestDispatcher(Links.APPOINTMENT_JSP);
        verify(request, never() ).getSession();
        verify(dispatcher).forward(request, response);

    }

    @Test
    public void testAddDoctorServletDoGet() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        AddDoctorServlet servlet = new AddDoctorServlet();
        when(request.getRequestDispatcher(Links.DOCTOR_JSP)).thenReturn(dispatcher);

        servlet.doGet(request, response);

        verify(request, times(1)).getRequestDispatcher(Links.DOCTOR_JSP);
        verify(request, never() ).getSession();
        verify(dispatcher).forward(request, response);

    }

    @Test
    public void testAddPatientServletDoGet() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        AddPatientServlet servlet = new AddPatientServlet();
        when(request.getRequestDispatcher(Links.PATIENT_JSP)).thenReturn(dispatcher);

        servlet.doGet(request, response);

        verify(request, times(1)).getRequestDispatcher(Links.PATIENT_JSP);
        verify(request, never() ).getSession();
        verify(dispatcher).forward(request, response);

    }
    @Test
    public void AddReceptionistServletDoGet() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        AddReceptionistServlet servlet = new AddReceptionistServlet();
        when(request.getRequestDispatcher(Links.RECEPTIONIST_JSP)).thenReturn(dispatcher);

        servlet.doGet(request, response);

        verify(request, times(1)).getRequestDispatcher(Links.RECEPTIONIST_JSP);
        verify(request, never() ).getSession();
        verify(dispatcher).forward(request, response);

    }





//    @Test
//    public void testAddAppointmentServletDoPost() throws Exception {
//        HttpServletRequest request = mock(HttpServletRequest.class);
//        HttpServletResponse response = mock(HttpServletResponse.class);
//        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
//
//        AddAppointmentServlet servlet = new AddAppointmentServlet();
//
//                when(request.getParameter("doctor")).thenReturn("3");
//        when(request.getParameter("patient")).thenReturn("3");
//        when(request.getParameter("time")).thenReturn("01:00 PM");
//        when(request.getParameter("date")).thenReturn("2020-05-01");
//
//
//        when(request.getRequestDispatcher(Links.APPOINTMENT_JSP)).thenReturn(dispatcher);
//        servlet.init();
//        servlet.doPost(request, response);
//
//        verify(request, times(1)).getRequestDispatcher(Links.APPOINTMENT_JSP);
//        verify(response, times(1)).sendRedirect(Links.APPOINTMENT_LIST_JSP);
//        verify(request, times(1) ).getSession();
//        verify(dispatcher).forward(request, response);
//    }

}
