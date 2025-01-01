INSERT INTO public.portfolio(id, user_id, available_funds, funds_locked, last_updated)
VALUES
(1, 1, 2000, 0, '2024-12-27 10:00:00');


INSERT INTO public.stock (symbol, quantity, quantity_lock, price, last_updated, portfolio_id)
VALUES
('AAPL', 50, 0, 150.25, '2024-12-27 10:00:00', 1),
('TSLA', 20, 0, 700.00, '2024-12-27 10:15:00', 1),
('GOOGL', 10, 0, 2800.50, '2024-12-27 10:05:00', 1);

