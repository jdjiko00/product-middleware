# Product Middleware API

This project is a REST API middleware designed to fetch, filter, and serve product data. It is built using the latest industry standards (Java 25 and Spring Boot 4.1.0) and features an in-memory database, basic authentication, and full Docker support.

---

## 1. Configuration and Setup (Database & Services)

The application is designed to be "plug-and-play" with zero external dependencies required for the local setup.
* **Database:** Uses an **H2 In-Memory database**. You do not need to install MySQL or PostgreSQL.
* **Data Seeding:** On application startup, the database is automatically populated with initial mock products via the `data.sql` script.
* **Service:** The application runs on an embedded Apache Tomcat server on port `8080`.

---

## 2. Authentication & Authorization

The API is secured using **Spring Security** with HTTP Basic Authentication. All endpoints under `/api/**` require authorization.

Please use the following test user credentials to access the API:
* **Username:** `admin`
* **Password:** `pass123`
* **Role:** `ADMIN`

---

## 3. How to Run Locally (Docker Setup)

The project includes a multi-stage `Dockerfile` that automatically handles the Maven build process and packages the application into a lightweight Alpine Java 25 image.

1. Ensure **Docker** (or Docker Desktop) is running on your machine.
2. Open a terminal in the root directory of the project (where this README is located).
3. **Build the image:**
   ```bash
   docker build -t product-middleware .
   ```
4. **Run the container:**
   ```bash
   docker run -p 8080:8080 product-middleware
   ```
The application will now be accessible at `http://localhost:8080`.

---

## 4. How to Test the Application

The easiest and recommended way to test the application is via the integrated **Swagger UI (OpenAPI)**.

**Step-by-step testing guide:**
1. Start the application (either via Docker or your IDE).
2. Open your web browser and navigate to:  
   - **http://localhost:8080/swagger-ui/index.html**
3. Click the green **"Authorize"** button at the top right of the page.
4. Expand the `GET /api/products` endpoint and click **"Try it out"**.
5. You can now test the advanced filtering logic by entering values into the optional fields (e.g., set `category`, `minPrice`, `maxPrice`, or `name`).
6. Click **"Execute"**.
7. Enter the test credentials (`admin` / `pass123`) and click Sign in.
8. You can now see the 200 OK response with the filtered JSON data.

**Testing via Postman (Optional):**
If you prefer Postman, send a `GET` request to `http://localhost:8080/api/products`. Ensure you select **Basic Auth** under the Authorization tab and input the test credentials.