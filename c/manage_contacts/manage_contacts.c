
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/*****************************************************************************/
/** Constants **/
/*****************************************************************************/
#define LINE_LENGTH 100
#define NAME_LENGTH 51
#define RELATIONSHIP_PHONE_TYPE_LENGTH 21
#define MAX_CONTACTS 100
const char *CONTACTS_FILE = "contacts.txt";

/*****************************************************************************/
/** Typedefs **/
/*****************************************************************************/
typedef enum
{
    FALSE = 0,
    TRUE = 1
} Boolean;

typedef struct
{
    int id;
    char name[NAME_LENGTH];
    char relationship[RELATIONSHIP_PHONE_TYPE_LENGTH];
} Contact;

typedef struct
{
    int id;
    char type[RELATIONSHIP_PHONE_TYPE_LENGTH];
    int area_code;
    int number;
} PhoneNumber;

/*****************************************************************************/
/** Global Variables **/
/*****************************************************************************/
Contact contacts[MAX_CONTACTS];
PhoneNumber phone_numbers[MAX_CONTACTS * 3];
int contact_count = 0;
int phone_number_count = 0;

/*****************************************************************************/
/** Utility Declarations **/
/*****************************************************************************/
void remove_newline_ch(char *line);
void swap(int idx, int idy, Boolean sort_contacts);
void selection_sort(Boolean sort_contacts);
void process_line(char line[LINE_LENGTH], Boolean is_name_line, int contact_id);
void read_contacts_file();

/*****************************************************************************/
/** Interface Declarations (for tests) **/
/*****************************************************************************/
int new_contact(char name[], char relationship[]);                 // name is mandatory and unique, relationship may be empty
Boolean delete_contact(int contact);                               // contact ID as returned from new_contact
Boolean add_phone(int contact, int area, int number, char type[]); // area and number must be valid
Boolean remove_phone(int contact, char type[]);                    // phone of that type must exist
void print_contacts(Boolean with_phone);
Boolean save_contacts(char filename[]);
int count_contacts();
int count_contact_numbers(int id);
int count_all_numbers();

/*****************************************************************************/
/** CLI Declarations **/
/*****************************************************************************/
void read_user_response(char response[]);

/*****************************************************************************/
/** Utility Definitions **/
/*****************************************************************************/
void remove_newline_ch(char *line)
{
    int new_line = strlen(line) - 1;
    if (line[new_line] == '\n')
        line[new_line] = '\0';
}

void swap(int idx, int idy, Boolean sort_contacts)
{ // sort_contacts = true for contacts, false for phone_numbers
    if (sort_contacts)
    {
        Contact temp = contacts[idx];
        contacts[idx] = contacts[idy];
        contacts[idy] = temp;
    }
    else
    {
        PhoneNumber temp = phone_numbers[idx];
        phone_numbers[idx] = phone_numbers[idy];
        phone_numbers[idy] = temp;
    }
}

void selection_sort(Boolean sort_contacts)
{
    if (sort_contacts)
    {
        for (int i = 0; i < contact_count - 1; i++)
        {
            int min_idx = i;
            for (int j = i + 1; j < contact_count; j++)
            {
                if (strcmp(contacts[j].name, contacts[min_idx].name) < 0)
                {
                    min_idx = j;
                }
            }
            swap(i, min_idx, sort_contacts);
        }
    }
    else
    {
        for (int i = 0; i < phone_number_count - 1; i++)
        {
            int min_idx = i;
            for (int j = i + 1; j < phone_number_count; j++)
            {
                if (strcmp(phone_numbers[j].type, phone_numbers[min_idx].type) < 0)
                {
                    min_idx = j;
                }
            }
            swap(i, min_idx, sort_contacts);
        }
    }
}

void process_line(char line[LINE_LENGTH], Boolean is_name_line, int contact_id)
{
    // Remove trailing newline character
    remove_newline_ch(line);

    char *token;
    int token_count = 0;
    if (is_name_line)
    {
        const char delimeter[] = ",";
        token = strtok(line, delimeter);

        Contact contact;
        contact.id = contact_id;
        while (token != NULL)
        {
            if (token_count == 0)
            { // Name
                // If the length of the name is greater than NAME_LENGTH, exit
                if (strlen(token) > NAME_LENGTH)
                {
                    printf("Error: Name too long\n");
                    return;
                }
                strcpy(contact.name, token);
            }
            else if (token_count == 1)
            { // Relationship
                // If the length of the relationship is greater than
                // RELATIONSHIP_PHONE_TYPE_LENGTH, exit
                if (strlen(token) > RELATIONSHIP_PHONE_TYPE_LENGTH)
                {
                    printf("Error: Relationship too long\n");
                    return;
                }
                strcpy(contact.relationship, token);
            }
            else
            {
                printf("Error: Too many tokens in name line\n");
                return;
            }
            token_count++;
            token = strtok(NULL, delimeter);
        }
        contacts[contact_count++] = contact;
    }
    else
    {
        // Phone number
        const char delimeter[] = " ";
        token = strtok(line, delimeter);
        PhoneNumber phone_number;
        phone_number.id = contact_id;
        while (token != NULL)
        {

            if (token_count == 0)
            { // Area code
                phone_number.area_code = atoi(token);
            }
            else if (token_count == 1)
            { // Number
                phone_number.number = atoi(token);
            }
            else if (token_count == 2)
            { // Type
                strcpy(phone_number.type, token);
            }
            else if (token_count > 2)
            {
                char *temp = strcat(phone_number.type, " ");
                // If the length of the type is greater than
                // RELATIONSHIP_PHONE_TYPE_LENGTH, exit
                if (strlen(temp) + strlen(token) > RELATIONSHIP_PHONE_TYPE_LENGTH)
                {
                    printf("Error: Type too long\n");
                    return;
                }
                strcpy(phone_number.type, strcat(temp, token));
            }
            token_count++;
            token = strtok(NULL, delimeter);
        }
        phone_numbers[phone_number_count++] = phone_number;
    }
}

void read_contacts_file()
{
    char line[LINE_LENGTH];
    FILE *fp = fopen(CONTACTS_FILE, "r");
    if (fp == NULL)
    {
        printf("Error opening file!\n");
        exit(1);
    }

    Boolean new_contact = TRUE;
    int contact_id = contact_count + 1;
    while (fgets(line, LINE_LENGTH, fp) != NULL)
    {
        if (line[0] == '\n')
        {
            // End of contact information
            new_contact = TRUE;
            contact_id++;
            continue;
        }

        process_line(line, new_contact, contact_id);
        new_contact = FALSE;
    }
}

/*****************************************************************************/
/** Interface definitions */
/*****************************************************************************/
void print_contacts(Boolean with_phone)
{
    // Sort contacts by name
    selection_sort(TRUE);
    // Then sort phone numbers by type
    selection_sort(FALSE);
    // Finally, print contacts.
    for (int i = 0; i < contact_count; i++)
    {
        Contact contact = contacts[i];
        if (strlen(contact.relationship) != 0)
        {
            printf("%d. %s (%s)\n", (i + 1), contact.name, contact.relationship);
        }
        else
        {
            printf("%d. %s\n", (i + 1), contact.name);
        }

        if (with_phone)
        {
            for (int j = 0; j < phone_number_count; j++)
            {
                PhoneNumber phone_number = phone_numbers[j];
                if (phone_number.id == contact.id)
                {
                    printf("   %s (%d) %d\n", phone_number.type, phone_number.area_code, phone_number.number);
                }
            }
        }
    }
}

int new_contact(char name[], char relationship[])
{
    // If length of name is greater than NAME_LENGTH, return -1
    // Or if length of relationship is greater than
    // RELATIONSHIP_PHONE_TYPE_LENGTH, return -1
    if (strlen(name) > NAME_LENGTH || strlen(relationship) > RELATIONSHIP_PHONE_TYPE_LENGTH)
    {
        printf("Error: Name or relationship too long\n");
        return -1;
    }
    // Check if name is unique
    for (int i = 0; i < contact_count; i++)
    {
        if (strcmp(contacts[i].name, name) == 0)
        {
            printf("Error: Name already exists\n");
            return -1;
        }
    }

    Contact contact;
    contact.id = contact_count + 1;
    remove_newline_ch(name);
    strcpy(contact.name, name);
    if (strlen(relationship) != 0)
    {
        remove_newline_ch(relationship);
        strcpy(contact.relationship, relationship);
    }
    contacts[contact_count++] = contact;
    return contact.id;
}

int count_contacts()
{
    return contact_count;
}

int count_all_numbers()
{
    return phone_number_count;
}

int count_contact_numbers(int id)
{
    int count = 0;
    for (int i = 0; i < phone_number_count; i++)
    {
        if (phone_numbers[i].id == id)
        {
            count++;
        }
    }
    return count;
}

Boolean delete_contact(int contact)
{
    Boolean found = FALSE;
    for (int i = 0; i < contact_count; i++)
    {
        if (contacts[i].id == contact)
        {
            found = TRUE;
            // Remove contact
            for (int j = i; j < contact_count - 1; j++)
            {
                contacts[j] = contacts[j + 1];
            }
            contact_count--;
            break;
        }
    }
    return found;
}

Boolean add_phone(int contact, int area, int number, char type[])
{
    // area and number must be valid
    if (area < 100 || area > 999 || number < 1000000 || number > 9999999)
    {
        printf("Error: Invalid area code or number\n");
        return FALSE;
    }
    // If length of type is greater than RELATIONSHIP_PHONE_TYPE_LENGTH, return
    // FALSE
    if (strlen(type) > RELATIONSHIP_PHONE_TYPE_LENGTH)
    {
        printf("Error: Type too long\n");
        return FALSE;
    }

    Boolean added = FALSE;
    for (int i = 0; i < contact_count; i++)
    {
        if (contacts[i].id == contact)
        {
            added = TRUE;
            PhoneNumber phone_number;
            phone_number.id = contact;
            phone_number.area_code = area;
            phone_number.number = number;
            if (strlen(type) != 0)
            { // type is optional. If empty, don't add it
                remove_newline_ch(type);
                strcpy(phone_number.type, type);
            }
            phone_numbers[phone_number_count++] = phone_number;
            break;
        }
    }
    return added;
}

Boolean remove_phone(int contact, char type[])
{
    Boolean removed = FALSE;
    for (int i = 0; i < phone_number_count; i++)
    {
        if (phone_numbers[i].id == contact && strcmp(phone_numbers[i].type, type) == 0)
        {
            removed = TRUE;
            // Remove phone number
            for (int j = i; j < phone_number_count - 1; j++)
            {
                phone_numbers[j] = phone_numbers[j + 1];
            }
            phone_number_count--;
            break;
        }
    }
    return removed;
}

Boolean save_contacts(char filename[])
{
    Boolean saved = FALSE;
    FILE *fp = fopen(filename, "w");
    if (fp == NULL)
    {
        printf("Error opening file!\n");
        return saved;
    }
    // Alternatively, print_contacts(TRUE) > filename would work.
    // Save contacts to file
    saved = TRUE;
    for (int i = 0; i < contact_count; i++)
    {
        Contact contact = contacts[i];
        if (strlen(contact.relationship) != 0)
        {
            fprintf(fp, "%d. %s (%s)\n", (i + 1), contact.name, contact.relationship);
        }
        else
        {
            fprintf(fp, "%d. %s\n", (i + 1), contact.name);
        }
        // Print the phone numbers
        for (int j = 0; j < phone_number_count; j++)
        {
            PhoneNumber phone_number = phone_numbers[j];
            if (phone_number.id == contact.id)
            {
                fprintf(fp, "   %s (%d) %d\n", phone_number.type, phone_number.area_code, phone_number.number);
            }
        }
    }

    return saved;
}

/*****************************************************************************/
/** CLI Definitions **/
/*****************************************************************************/
// TODO: Implement CLI interface for the program to interact with the user
/*****************************************************************************/
/** Main **/
/*****************************************************************************/
int main()
{
    read_contacts_file();
    print_contacts(TRUE);
    return 0;
}
