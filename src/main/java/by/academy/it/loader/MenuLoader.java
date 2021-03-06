package by.academy.it.loader;

import by.academy.it.database.exception.DaoException;
import by.academy.it.domain.Address;
import by.academy.it.domain.Department;
import by.academy.it.domain.Person;
import by.academy.it.factory.DaoFactrory;
import org.apache.log4j.Logger;

import java.util.Scanner;

import static by.academy.it.loader.AddressMenu.findAddress;
import static by.academy.it.loader.DepartmentMenu.*;
import static by.academy.it.loader.PersonMenu.*;

/**
 * Created by alexanderleonovich on 13.05.15.
 * Class for load menu in console and for execute operations with entities
 */
public class MenuLoader {

    private static Logger log = Logger.getLogger(MenuLoader.class);
    private static Boolean needMenu = true;
    public DaoFactrory factory;

    public MenuLoader() {
        this.factory = DaoFactrory.getInstance();
    }

    public void menu() throws DaoException {
        Person person = null;
        Address address = null;
        Department department = null;
        Integer id;
        while (needMenu) {
            printMenu();
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            switch (choice) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    // Delete Person
                    person = findPerson();
                    factory.getPersonDao().delete(person);
                    break;
                case 2:
                    //Get Person
                    person = findPerson();
                    //address = person.getAddress();
                    //System.out.println("\n" + address);
                    break;
                case 3:
                    // Load Person
                    person = loadPerson();
                    break;
                case 4:
                    // Save Person with Address
                    person = null;
                    person = createPerson(person);
                    address = null;
                    address = createAddress(address);
                    person.setAddress(address);
                    address.setPerson(person);
                    //getPersonDao().save(person);
                    log.info("Saved Address-object " + address.toString());
                    factory.getAddressDao().save(address);
                    break;
                case 5:
                    // Delete address
                    address = findAddress();
                    factory.getAddressDao().delete(address);
                    break;
                case 6:
                    // SAVE OR UPDATE ADDRESS //TODO update только адрес!:) why?
                    person = createPerson(person);
                    address = createAddress(address);
                    person.setAddress(address);
                    address.setPerson(person);
                    factory.getAddressDao().saveOrUpdate(address);
                    break;
                case 7:
                    getAllPersons();
                    break;
                case 8:
                    person = createPerson(person);
                    address = createAddress(address);
                    person.setAddress(address);
                    address.setPerson(person);
                    factory.getPersonDao().update(person);
                    break;
                case 9:
                    findAddress();
                    break;
                case 10:
                    department = loadDepartment();
                    factory.getDepartmentDao().delete(department);
                    break;
                case 11:
                    department = findDepartment();
                    break;
                case 12:
                    loadDepartment();
                    break;
                case 13:
                    department = null;
                    department = createDepartment(department);
                    department.setId(getIdForSave());
                    factory.getDepartmentDao().save(department);
                    break;
                case 14:
                    department = null;
                    department = createDepartment(department);
                    id = getIdForSave();
                    department.setId(id);
                    factory.getDepartmentDao().save(department, String.valueOf(id));
                    break;
                case 15:
                    department = createDepartment(department);
                    factory.getDepartmentDao().saveOrUpdate(department);
                    break;
                case 16:
                    getDepartments();
                    break;
                case 17:
                    department = createDepartment(department);
                    factory.getDepartmentDao().update(department);
                    break;
                case 18:
                    department = null;
                    department = createDepartment(department);
                    id = getIdForSave();
                    department.setId(id);
                    factory.getDepartmentDao().update(department, String.valueOf(id));
                    break;
                case 19:
                    flushPersonSession();
                    break;
                case 20:
                    flushDepartmentSession();
                    break;
                case 21:

                    break;
                case 22:
                    break;
                case 23:

                    break;
                default:
                    needMenu = true;
                    break;
            }
            needMenu = true;
        }
    }


    private static void printMenu() {
        System.out.println("\n+-----------------------------------------------+");
        System.out.println("|     Hello, user! You are in menu. Do action:  |");
        System.out.println("+------------------------------------------------------------------------------------------------------------------------------------------------------+");
        System.out.println("|       0. Exit          |   1. Delete Person   |          2. Get Person   |       3. Load Person       |4. Save Pers with Addr | 5. Delete Address |");
        System.out.println("+------------------------------------------------------------------------------------------------------------------------------------------------------+");
        System.out.println("| 6. Save or Update Addr | 7. Get All Persons   |     8. Update Person     |  9. Update Person with nam |10. Delete department  |     11. Get Depart   |");
        System.out.println("+------------------------------------------------------------------------------------------------------------------------------------------------------+");
        System.out.println("|   12. Load Department  | 13.  Save Department |  14. Save Depart with id |  15. Save or Update Depart | 16.  Get Depart list  | 17. Update Department|");
        System.out.println("+------------------------------------------------------------------------------------------------------------------------------------------------------+");
        System.out.println("|18. Upd Depart with name| 19. Flush Person sess| 20. Flush Depart session |  21. Save or update people |    22. Get People     |23.Save or update Empl|");
        System.out.println("+------------------------------------------------------------------------------------------------------------------------------------------------------+");
    }

    private int getIdForSave() {
        System.out.println("Write Id position for saving entity:");
        System.out.print("Id - ");
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        return id;
    }

}
