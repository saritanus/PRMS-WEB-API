package sg.edu.nus.iss.phoenix.schedule.restful;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.schedule.service.ScheduleService;

/**
 * @author Sarita Sethy
 * @version 1.0
 * @created 18-Sep-2017 10:56:45 PM
 */
@Path("Schedule")
public class ScheduleRESTService {
    
    @Context
    private UriInfo context;

    public ScheduleService service;
    public ScheduleRESTService(){
        service=new ScheduleService();

    }

    public void finalize() throws Throwable {

    }
    /* Getting all the program schedules from the database. By default its going to show todays date schedule*/
    
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public ProgramSlots getAllProgramSlots() throws SQLException
    {
        ArrayList<ProgramSlot> slotList = service.findAllProgramSlots();
        ProgramSlots psList = new ProgramSlots();
        psList.setSlotList(new ArrayList<>());        
        for (int i = 0; i < slotList.size(); i++) 
        {
                psList.getSlotList().add(
                new ProgramSlot(slotList.get(i).getId(), 
                        slotList.get(i).getDuration(),
                        slotList.get(i).getStartTime(),
                        slotList.get(i).getEndTime(),
                        slotList.get(i).getWeekId(),
                        slotList.get(i).getPresenter(),
                        slotList.get(i).getProducer(),
                        slotList.get(i).getRadioProgram()));                  
        }

        return psList;
    }
/*	
    @PUT
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
	public void createSchedule(ProgramSlot ps){
            service.processCreate(ps);

	}

	
@POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
	public void updateSchedule(ProgramSlot ps) throws SQLException{
            service.processModify(ps);

	}
        
    @DELETE
    @Path("/delete/{slot}")
    @Consumes(MediaType.APPLICATION_JSON)
        public void deleteProgramSlot(@PathParam("slot") ProgramSlot programslot) {
        service.processDelete(programslot);
    }*/
}//end ScheduleRESTService