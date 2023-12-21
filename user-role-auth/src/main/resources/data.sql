INSERT INTO public.permission(id,name) VALUES(1,'Admin-FullAccess')
    ON CONFLICT (name) DO NOTHING;

INSERT INTO public.users(id, email, first_name, last_name, password)
VALUES (1, 'admin@example.com', 'John', 'Doe', '$2a$10$B2GLoe8yN7Z5DVVERWXnj.O1zlczKNNktiXgMRktl6Y7fi/mvktSK')
    ON CONFLICT (email) DO NOTHING;
-- pwd1234

INSERT INTO public.role(id,created_date_time, name, created_user_id) VALUES (1, 1699575689468, 'Admin_Default', 1)
    ON CONFLICT (id) DO NOTHING;

INSERT INTO public.role_permission_reference(role_id, permission_id) VALUES (1, 1)
    ON CONFLICT (role_id,permission_id) DO NOTHING;

INSERT INTO public.user_role_reference(role_id, user_id, created_date_time)
VALUES (1, 1, 1699575689468)
    ON CONFLICT (role_id, user_id) DO NOTHING;